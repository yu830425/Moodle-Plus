package ntou.basic;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import ch.makery.address.UploadTrigger;
import ntou.chupei.Console;

public class Assignment 
{
	public HtmlPage page;
	public String name;
	public String content;
	public Date deadLine;
	public boolean hasDeadLine = true;;
	
	private ArrayList<Homework> homeworks;
	
	public Assignment(String initName,HtmlPage initPage,String time)
	{
		name = initName;
		page = initPage;
		
		time = time.replaceAll("¦~[ ]+|¤ë[ ]+", "/");
		time = time.replaceAll("¤é", "");
		time = time.replaceAll("[(].*[)]", "");
		
		time += ":00";
		
		SimpleDateFormat formater = new SimpleDateFormat();
		formater.applyPattern("yyyy/MM/dd HH:mm:ss");
		
		try 
		{
			if(time.compareTo("-:00") == 0)
			{
				deadLine = formater.parse("0000/01/01 00:00:00");
				hasDeadLine = false;
			}
			else
			{
				deadLine = formater.parse(time);
				hasDeadLine = true;
			}
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
 		homeworks = new ArrayList<Homework>();
		
		updateHomework();
	}
	
	public void upload(String filePath)
	{
		HtmlInput inputFile = page.getElementByName("newfile");
		
		inputFile.setAttribute("value", filePath);
		
		HtmlInput submit = page.getElementByName("save");
		
		UploadEvent event = new UploadEvent(filePath, inputFile, submit);
		UploadTrigger.uploadEvent(event);
		/*
		try 
		{
			submit.click();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		*/
	}
	
	public void updateHomework()
	{
		;
	}
}