package ch.makery.address;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkStatus extends Thread 
{
	public static int statusCode = 400;
	
	public void run() 
	{ 
		while(true)
		{
			try 
			{
				InetAddress address = InetAddress.getByName("140.121.81.101");
				
				if(address.isReachable(5000))
				{  
					System.out.println("SUCCESS");
					statusCode = 200;
				}
				else
				{ 
					System.out.println("FAILURE");
					statusCode = 400;
				} 
				
				sleep(5000);
			} 
			catch (UnknownHostException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
    }
}
