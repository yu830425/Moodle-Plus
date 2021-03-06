package ntou.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import ch.makery.address.*;

import ntou.chupei.Console;

public class Course 
{
	public String courseName;
	public String year;
	
	private HtmlPage home;
	private HtmlPage homework;
	private boolean hasHomework;
	private HtmlPage rootResource;
	private HtmlPage resource;
	
	public ArrayList<Assignment> assignmentList;
	public ArrayList<Handout> handoutList;
	
	public Course(String name,String time,HtmlPage initPage)
	{
		/**
		 * Variable Initial
		 */
		courseName = name;
		year = time;
		
		
		/**
		 * Pages Initial
		 */
		home = initPage;
		
		/**
		 * List Initial 
		 */
		assignmentList = new ArrayList<Assignment>();
		handoutList = new ArrayList<Handout>();
		
		/**
		 * Locate the homwork page, and initialize the homework list.
		 */
		ArrayList actionList = (ArrayList) home.getByXPath("//*[@id=\"inst5\"]/div[2]/ul/li");
		Iterator it = actionList.iterator();
		hasHomework = false;
		
		while(it.hasNext())
		{
			HtmlListItem nodeGroup = (HtmlListItem) it.next();
			HtmlAnchor link = (HtmlAnchor) nodeGroup.getByXPath(".//a").get(0);
			
			String nodeName = link.asText();		
			
			
			if(nodeName.compareTo("作業") == 0)
			{
				try 
				{
					homework = link.click();
					hasHomework = true;
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block 
					e.printStackTrace();
				}
			}
		}
		
		if(hasHomework == true)		
			updateHomeworkList();
		
		/**
		 *  Locate handout page and initialize the handout list.
		 */
		actionList = (ArrayList) home.getByXPath("//*[@id=\"section-0\"]//li/a");
		it = actionList.iterator();
		
		while(it.hasNext())
		{
			HtmlAnchor link = (HtmlAnchor) it.next();
			String linkName = link.asText();
			
			if(linkName.compareTo("檔案下載區") == 0)
			{
				try 
				{
					rootResource = link.click();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(rootResource != null)
		{
			updateHandout(rootResource);
			
			actionList = (ArrayList) rootResource.getByXPath("//*[@id=\"content\"]/div/table/tbody/tr/td/a");
			it = actionList.iterator();
			
			while(it.hasNext())
			{
				HtmlAnchor link = (HtmlAnchor) it.next();
				String linkName = link.asText();
				
				if(linkName.compareTo(" 課程講義") == 0)
				{
					try 
					{
						resource = link.click();
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if(resource != null)
				updateHandout(resource);
		}
	}
	               
	public void updateHomeworkList()
	{
		ArrayList list = (ArrayList) homework.getByXPath("//*[@id=\"content\"]/table//tr/td/a[contains(@href,\"view.php\")]");
		
		Iterator it = list.iterator();
		
		while(it.hasNext())
		{
			HtmlAnchor anchor = (HtmlAnchor) it.next();
			
			HtmlTableCell tableRow = anchor.getParentNode().getParentNode().getFirstByXPath(".//td[contains(@class,\"c3\")]");
			
			try 
			{
				assignmentList.add(new Assignment(anchor.asText(),anchor.click(),tableRow.asText()));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void updateHandout(HtmlPage page)
	{		
		ArrayList fileList = (ArrayList) page.getByXPath("//*[@id=\"content\"]/div/table/tbody/tr[@class=\"file\"]");
		
		Iterator it = fileList.iterator();
		
		while(it.hasNext())
		{
			HtmlTableRow fileNode = (HtmlTableRow) it.next();
			
			HtmlAnchor link = (HtmlAnchor) fileNode.getByXPath("./td[1]/a").get(0);
			String fileName = link.asText();
			String sizeString = ((HtmlTableDataCell)fileNode.getByXPath("./td[3]").get(0)).asText();
			
			try 
			{
				/*
				InputStream fileStream = link.click().getWebResponse().getContentAsStream();
				
				String path = "C:/Users/Chupei/Desktop/OutputFile/"+Services.crawler.userID+"/"+courseName;
				
				File filePath = new File(path);
				filePath.mkdirs();
				
				OutputStream outputStream = new FileOutputStream(new File(path+"/"+fileName));
				
				int data = fileStream.read();
				
				while(data != -1)
				{
					outputStream.write(data);
					data = fileStream.read();
				}
				
				outputStream.flush();
				outputStream.close();
				*/
				InputStream fileStream = link.click().getWebResponse().getContentAsStream();
				
				String path = "./"+Services.crawler.userID+"/"+year+"/"+courseName;
				
				File filePath = new File(path);
				filePath.mkdirs();
				
				Handout temp = new Handout(path+"/"+fileName);
				
				OutputStream outputStream = new FileOutputStream((File)temp);
				
				//125MB
				byte[] bytes = new byte[131072000];
				
				int size = fileStream.read(bytes);
				int totalSize = 0;
				
				while(size != -1)
				{
					outputStream.write(bytes,0,size);
					totalSize += size;
					
					size = fileStream.read(bytes);
				}
				
				outputStream.flush();
				outputStream.close();
				
				temp.fileName = fileName;
				temp.link = link;
				temp.fileSize = totalSize;
				
				handoutList.add(temp);
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (FailingHttpStatusCodeException code)
			{
				Console.warn("File Not Found!!");
				Console.warn(code.getMessage());
			}
		}		
	}
}
