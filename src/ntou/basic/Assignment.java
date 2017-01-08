package ntou.basic;

import java.io.IOException;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Assignment 
{
	public HtmlPage page;
	public String name;
	public String content;
	
	private ArrayList<Homework> homeworks;
	
	public Assignment(String initName,HtmlPage initPage)
	{
		name = initName;
		page = initPage;
		homeworks = new ArrayList<Homework>();
		
		updateHomework();
	}
	
	public void upload(String filePath)
	{
		HtmlInput inputFile = page.getElementByName("newfile");
		
		inputFile.setAttribute("value", filePath);
		
		HtmlInput submit = page.getElementByName("save");
		
		try 
		{
			submit.click();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateHomework()
	{
		;
	}
}
