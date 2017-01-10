package ch.makery.address;

import ntou.chupei.Console;
import ntou.chupei.Crawler;

public class Services 
{
	public static Crawler crawler;
	
	public static void initCrawler(String acc,String passwd)
	{
		crawler = new Crawler();
		
		if(crawler.login(acc, passwd))
		{
			Console.log("[Crawler] - Login Success.");
			
			crawler.updateCourse();
		}
		else
		{
			Console.log("[Crawler] - Login Failed!");
		}
	}
}
