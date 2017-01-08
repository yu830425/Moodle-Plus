package ntou.basic;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class Handout 
{
	private HtmlAnchor link;
	public String fileName;
	public int fileSize;
	
	public Handout(HtmlAnchor initLink,String initName)
	{
		link = initLink;
		fileName = initName;
	}
	
	public void download()
	{
		;
	}
}
