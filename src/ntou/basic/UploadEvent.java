package ntou.basic;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.HtmlInput;

public class UploadEvent 
{
	private String path;
	private HtmlInput inputFile;
	private HtmlInput submit;
	
	public UploadEvent(String filePath,HtmlInput inputFile, HtmlInput submit)
	{
		filePath = path;
		this.inputFile = inputFile;
		this.submit = submit;
	}
	
	public void upload()
	{
		try 
		{
			submit.click();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
