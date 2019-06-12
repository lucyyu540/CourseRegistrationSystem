package edu.nyu.cs.jsy332;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements StudentMethods, java.io.Serializable{
	public static final String username = "Student";
	public static final String password = "Student001";
	//all new students created have default no registered classes
	private ArrayList<Course> registeredCourses = new ArrayList<Course>();
	//deserializing
	

			
	/**
	 * constructor for new student
	 */
	public Student() {
	
	}
	/**
	 * overloaded constructor
	 * @param first
	 * @param last
	 */
	public Student(String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}

	//setter for registered courses
	public void registerCourse(Course course) {
		registeredCourses.add(course);
	}
	//getter for full name
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	//getter for first name
	public String getFirst() {
		return this.firstName;
	}
	//getter for last name
	public String getLast() {
		return this.lastName;
	}
	public ArrayList<Course> getCourses() {
		return this.registeredCourses;
	}
	
	//1 viewAllCourses in parent class
	
	/**
	 * 2
	 * this method goes through all the courses available, compares the num of max vs num of current students
	 * and prints out the non full courses
	 */
	@Override
	public void viewNotFull(ArrayList<Course> courses) {
		ArrayList<Course> notFullCourses = new ArrayList<Course>();
		for (Course course: courses) {
			if(course.isNotFull()) {
				notFullCourses.add(course);
			}
		}
		viewAllCourses(notFullCourses);
	}
	 
	/**
	 * 3
	 * this method adds a specified student to a course's roster
	 * and adds the course to the student's array-list of registered courses. 
	 * @param student
	 * @param courses
	 * @param courseName
	 * @param section
	 */
	public void register(ArrayList<Course> courses) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter course name:");
		String courseName = scn.nextLine();
		System.out.println("Enter course section:");
		int section = Integer.parseInt(scn.nextLine());
		for (Course course: courses) {
			if(course.getName().equalsIgnoreCase(courseName) && course.getSection() == section) {
				//if the course is not full add the student to the course, and add the course to student's registered courses
				if (course.isNotFull()) {
					course.addStudent(this);
					this.registerCourse(course);
				}
				else {
					System.out.println("The course is full");
				}
			}
		}
	}
	
	public void removeCourse(Course course) {
		this.registeredCourses.remove(course);
	}
	
	
	/**
	 * 4
	 * removes the student from the roster in a given course
	 * and also removes the course from the list of registeredCourses
	 */
	@Override
	public void withdraw(ArrayList<Course> courses) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter course ID");
		String id = scn.nextLine();
		System.out.println("Enter course section:");
		int section = Integer.parseInt(scn.nextLine());
		
		Course removeIt = null;
		boolean shouldRemove = false;
		for (Course course : this.registeredCourses) {
			if(course.getId().equalsIgnoreCase(id) && course.getSection() == section) {
				removeIt = course;
				shouldRemove = true;
			}
		}
		
		if (shouldRemove) {
			this.removeCourse(removeIt);
			removeIt.removeStudent(this);
		}
	
	}
	
	@Override
	/**
	 * 5 
	 * prints out all the courses the student is registered in 
	 */
	public void viewRegistered() {
		viewAllCourses(this.registeredCourses);
	}
	
}
