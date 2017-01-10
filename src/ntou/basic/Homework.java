package ntou.basic;

import java.io.File;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class Homework extends File
{
	private HtmlAnchor link;
	public String fileName;
	public int fileSize;
	
	public Homework(String arg0)
	{
		super(arg0);
	}
	
	public void download()
	{
		
	}
}
