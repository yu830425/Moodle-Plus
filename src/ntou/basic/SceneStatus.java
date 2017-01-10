package ntou.basic;

public class SceneStatus 
{
	public static String statusString = "No status";
	public static int statusCode = 0;
	
	public static void resetStatus()
	{
		statusString = "No status";
		statusCode = 0;
	}
	
	public static void changeStatus(String statStr,int code)
	{
		statusString = statStr;
		statusCode = code;
	}
}
