package ntou.chupei;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.w3c.css.sac.CSSException;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.ErrorHandler;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.InteractivePage;
import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParserListener;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;

import ch.makery.address.MyList;
import ntou.basic.Course;

public class Crawler 
{
	/**
	 * Private Variable 
	 */	
	private String rootURL = "http://moodle.ntou.edu.tw/";
	private WebClient client;	
	private HtmlPage homePage;
	
	/**
	 * Public Variable
	 */
	public String userName = "";
	public String userID = "";
	
	/**
	 * Private Method
	 */
	public ArrayList<Course> courseList;
	
	private void setCrawlerNoWarning()
	{
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

	    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
	    java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
		
		client.getOptions().setCssEnabled(false);

		client.setIncorrectnessListener(new IncorrectnessListener() {

	        @Override
	        public void notify(String arg0, Object arg1) {
	            // TODO Auto-generated method stub

	        }
	    });
		
	    client.setCssErrorHandler(new ErrorHandler() {

	        @Override
	        public void warning(CSSParseException exception) throws CSSException {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void fatalError(CSSParseException exception) throws CSSException {
	            // TODO Auto-generated method stub

	        }

	        @Override
	        public void error(CSSParseException exception) throws CSSException {
	            // TODO Auto-generated method stub

	        }
	    });
	    client.setJavaScriptErrorListener(new JavaScriptErrorListener() {

			@Override
			public void loadScriptError(InteractivePage arg0, URL arg1, Exception arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void malformedScriptURL(InteractivePage arg0, String arg1, MalformedURLException arg2) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void scriptException(InteractivePage arg0, ScriptException arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void timeoutError(InteractivePage arg0, long arg1, long arg2) {
				// TODO Auto-generated method stub
				
			}
	    });
	    client.setHTMLParserListener(new HTMLParserListener() {
			@Override
			public void error(String arg0, URL arg1, String arg2, int arg3, int arg4, String arg5) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void warning(String arg0, URL arg1, String arg2, int arg3, int arg4, String arg5) {
				// TODO Auto-generated method stub
				
			}
	    });

	    client.getOptions().setThrowExceptionOnFailingStatusCode(false);
	    client.getOptions().setThrowExceptionOnScriptError(false);
	}
	
	/**
	 * Constructor
	 */
	public Crawler()
	{		
		client = new WebClient(BrowserVersion.FIREFOX_45);		
		client.getOptions().setThrowExceptionOnScriptError(false);
		
		setCrawlerNoWarning();
		
		courseList = new ArrayList<Course>();
		
		try 
		{
			homePage = client.getPage(rootURL);
		} 
		catch (FailingHttpStatusCodeException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Public Method
	 */
	public boolean login(String account,String password)
	{
		//Locate the account and password element
		HtmlInput accElement = (HtmlInput) homePage.getElementById("login_username");
		HtmlInput passwdElement = (HtmlInput) homePage.getElementById("login_password");
		
		//Set Account and Password
		accElement.setAttribute("value", account);
		passwdElement.setAttribute("value", password);
		
		//Locate Login Button
		HtmlInput login = (HtmlInput) homePage.getByXPath("//*[@id=\"login\"]/div[3]/input").get(0);
		
		//Login
		try 
		{
			homePage = login.click();
			
			//Console.log(homePage.asXml());
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
		
		//Validation
		HtmlAnchor loginInfo = (HtmlAnchor) homePage.getByXPath("//*[@id=\"header\"]/table/tbody/tr/td/table/tbody/tr[1]/td[2]/div/div/a").get(0);
		String[] information = loginInfo.asText().split(" ");
		
		if(information.length == 2 && information[1].compareTo(account) == 0)
		{
			userName = information[0];
			userID = information[1];
			
			return true;
		}
		else
			return false;
	}
	
	public void updateCourse()
	{
		ArrayList list = (ArrayList) homePage.getByXPath("//div[contains(@id,\"objTreeMenu_1_node\")]");
		
		Iterator it = list.iterator();
		while(it.hasNext())
		{
			HtmlDivision courseNode = (HtmlDivision) it.next();
			
			if(courseNode.getAttribute("id").split("_").length != 7)
				continue;
			
			HtmlAnchor courseLink = (HtmlAnchor) courseNode.getByXPath(".//a").get(0);
			
			String[] param = courseLink.getAttribute("title").split("_");
						
			try 
			{
				if(param.length == 2)
					courseList.add(new Course(param[1],param[0],courseLink.click()));
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//saveResult();
	}
	
	public boolean loadResult()
	{
		MyList list;
		
		try 
		{
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("javaObjects.txt"));
			
			try 
			{
				list = (MyList) objectInputStream.readObject();
			} 
			catch (ClassNotFoundException e) 
			{
				e.printStackTrace();
				return false;
			}
			
			objectInputStream.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (Exception e)
		{
			return false;
		}
		
		courseList = new ArrayList<Course> (list.courseList);
		
		return true;
	}
	
	public void saveResult()
	{
		MyList mylist = new MyList(courseList);
		
		try 
		{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("javaObjects.txt"));
			
			objectOutputStream.writeObject(mylist);
			objectOutputStream.flush();
			objectOutputStream.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isLogin()
	{
		if(userName.compareTo("")==0)
			return false;
		else
			return true;
	}
	
	public void showCourseName()
	{
		Iterator it = courseList.iterator();
		
		while(it.hasNext())
		{
			System.out.println(((Course)it.next()).courseName);
		}
	}
	
	/**
	 * Get Method
	 */
	public String getUserName()
	{
		return userName;
	}
	
	public String getUserID()
	{
		return userID;
	}
}
