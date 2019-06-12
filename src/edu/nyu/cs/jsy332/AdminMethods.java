package edu.nyu.cs.jsy332;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface AdminMethods {
	public void createCourse(ArrayList<Course> courses);
	public void editCourse(ArrayList<Course> courses);
	public void viewStudentsInCourse(ArrayList<Course> courses);
	public void viewFull(ArrayList<Course> courses);
	public void writeFull(ArrayList<Course> courses) throws FileNotFoundException;
	public void viewOne(ArrayList<Course> courses);
	public void viewCourseForStudent(ArrayList<Student> students);
	public void addStudent(ArrayList<Student> students);
	public void sortCourses(ArrayList<Course> courses);
	public void deleteCourse(ArrayList<Course> courses, ArrayList<Student> students);
	
	
}
