package ch.makery.address;

import java.util.LinkedList;
import ntou.basic.UploadEvent;

public class UploadTrigger extends Thread
{
	public static LinkedList<UploadEvent> queue = new LinkedList<UploadEvent>();
	
	public void run() 
	{ 
		// override Thread's run()
        //System.out.println("Here is the starting point of Thread.");

	        for (;;) 
	        {        	
	        	if(!queue.isEmpty())
	        	{
	        		if(NetworkStatus.statusCode == 200)
	        		{
	        			UploadEvent buffer = queue.pollFirst();
	        			
	        			buffer.upload();
	        		}
	        	}
	        }
		
    }
	
	public static void uploadEvent(UploadEvent temp)
	{
		queue.add(temp);
	}
}
