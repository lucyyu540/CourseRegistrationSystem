package edu.nyu.cs.jsy332;

import java.util.ArrayList;
import java.util.Scanner;

public class User implements java.io.Serializable {
	protected String username;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected boolean keepGoing = true;
	public User() {
		}
	
	
	/**
	 * this method prints out the name of ALL courses in the array-list of courses
	 * @param courses
	 */
	public void viewAllCourses(ArrayList<Course> courses) {
		if (courses.size() == 0) {
			System.out.println("No courses to display.");
		}
		else {
			for (Course course: courses) {
				System.out.println(course.getName());
			}
		}
	}
	
	
	/**
	 * the two methods below control whether program continues
	 */
	public void exit() {
		this.keepGoing = false;
	}
	public void enter() {
		this.keepGoing = true;
	}
	public boolean getKeepGoing() {
		return this.keepGoing; 
	}
	
	

}
