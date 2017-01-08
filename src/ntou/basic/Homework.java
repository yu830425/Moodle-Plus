package ntou.basic;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class Homework 
{
	private HtmlAnchor link;
	public String fileName;
	public int fileSize;
	
	public Homework(HtmlAnchor initLink,String name)
	{
		link = initLink;
		fileName = name;
	}
	
	public void download()
	{
		
	}
}
