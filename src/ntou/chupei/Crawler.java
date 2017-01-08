package ntou.chupei;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

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
	ArrayList<Course> courseList;

	/**
	 * Constructor
	 */
	public Crawler(String account,String passwd)
	{
		client = new WebClient(BrowserVersion.FIREFOX_45);
		client.getOptions().setThrowExceptionOnScriptError(false);
		courseList = new ArrayList<Course>();
		
		try 
		{
			homePage = client.getPage(rootURL);
			
			if(login(account,passwd))
			{
				Console.log("Login Success!!");
				updateCourse();
			}
			else
			{
				Console.err("Login Fail!!");
			}
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
