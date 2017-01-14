package ch.makery.address;

import ntou.chupei.Console;
import ntou.chupei.Crawler;

public class Services 
{
	public static Crawler crawler;
	private static UploadTrigger thread = new UploadTrigger();
	private static NetworkStatus thread2 = new NetworkStatus();
	
	public static boolean initCrawler(String acc,String passwd)
	{
		crawler = new Crawler();
		
		if(crawler.login(acc, passwd))
		{
			Console.log("[Crawler] - Login Success.");
			

			crawler.updateCourse();
			
			//crawler.updateCourse();
			return true;
		}
		else
		{
			Console.log("[Crawler] - Login Failed!");
			return false;
		}
	}
	
	public static void initTrigger()
	{
		thread.start();
		thread2.start();
	}
}
