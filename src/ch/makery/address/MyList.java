package ch.makery.address;

import java.io.Serializable;
import java.util.ArrayList;

import ntou.basic.Course;

public class MyList implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Course> courseList;
	
	public MyList(ArrayList<Course> list)
	{
		courseList = list;
	}
}
