package ntou.basic;

import java.io.File;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class Handout extends File
{
	public Handout(String arg0) 
	{
		super(arg0);
	}

	public HtmlAnchor link;
	public String fileName;
	public int fileSize;
	
	public void download()
	{
		;
	}
}
