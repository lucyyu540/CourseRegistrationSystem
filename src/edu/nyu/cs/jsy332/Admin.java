package edu.nyu.cs.jsy332;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;

public class Admin extends User implements AdminMethods{
	public static final String username = "Admin";
	public static final String password = "Admin001";
	
	/**
	 * 1
	 * this overrides the viewAllCourses method in the User class
	 * and allows admin access to a more comprehensive description of all the courses
	 */
	@Override
	public void viewAllCourses(ArrayList<Course> courses) {
		for (Course course : courses) {
			System.out.println("Course name: "+ course.getName());
			System.out.println("Course ID: " + course.getId());
			System.out.println("Current no. of students: " + course.getCurrent());
			System.out.println("Max no. of students: " + course.getMax());
			System.out.println("");
		}
	}
	
	/**
	 * 2
	 * this methods goes through the list of all courses, and prints out all the full ones
	 */
	@Override
	public void viewFull(ArrayList<Course> courses) {
		ArrayList<Course> fullCourses = new ArrayList<Course>();
		for (Course course : courses) {
			//if the course is not not full = full, add it to the local list fullCourses
			if (!course.isNotFull()) {
				fullCourses.add(course);
			}
		}
		viewAllCourses(fullCourses); //print them out
	}
	
	/**
	 * 3
	 * this course writes a new file named full_courses.txt
	 * for all the courses that are full
	 */
	@Override
	public void writeFull(ArrayList<Course> courses) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("src/CoursesFull.txt");
		for (Course course: courses) {
			if (!course.isNotFull()) {
				writer.print(course.getName());
			}
		}
		writer.close();
	}
	/**
	 * 4
	 * this methods prints out a list of students registered in a specific course
	 */
	@Override
	public void viewStudentsInCourse(ArrayList<Course> courses) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter course name:");
		String courseName = scn.nextLine();
		System.out.println("Enter section:");
		int section = Integer.parseInt(scn.nextLine());
		for (Course course : courses) {
			if( course.getName().equals(courseName) && course.getSection() == section) {
				course.printStudentNames();				
			}
		}
	}

	
	/**
	 * 5
	 * this allows admin to see all the courses a student is registered in
	 * by calling a student method that prints the list of registeredCourses
	 */
	@Override
	public void viewCourseForStudent(ArrayList<Student> students) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter student's first name:");
		String first = scn.nextLine();
		System.out.println("Enter student's last name:");
		String last = scn.nextLine();
		for (Student someone : students) {
			if (someone.getFirst().equalsIgnoreCase(first) && someone.getLast().equalsIgnoreCase(last)) {
				someone.viewRegistered();
			}
		}
	}

	/**
	 * 6
	 * this method rearranges the list of courses
	 * in a descending order of the current num of students in each course
	 */
	@Override
	public void sortCourses(ArrayList<Course> courses) {
		Collections.sort(courses);
		viewAllCourses(courses);
	}
	
	/**
	 * 7
	 * this allows an admin to create a new course
	 * and requires the admin to specify all its details: course name, id, max, instructor, section, and location
	 * a new course is instantiated and added to the list of courses in CRS
	 */
	@Override
	public void createCourse(ArrayList<Course> courses) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter name of the new course:");
		String name = in.nextLine();
		System.out.println("Enter id of the new course:");
		String id = in.nextLine();
		System.out.println("Enter maximum number of students of the new course:");
		int max = Integer.parseInt(in.nextLine());
		System.out.println("Enter the name of the instructor of the new course:");
		String instructor = in.nextLine();
		System.out.println("Enter section number of the new course:");
		int section = Integer.parseInt(in.nextLine());
		System.out.println("Enter the location of the new course:");
		String location = in.nextLine();
		//instantiate a new course then add it to the list of courses in the system
		Course newCourse = new Course(name, id, max, instructor, section, location);
		courses.add(newCourse);
		//verify a course has been added
		System.out.println(newCourse.getName() + " added to University Courses.");
	}
	
	
	
	/**
	 * 8
	 * this removes a specified course from the list of all courses
	 * and also removes the course from all its students' list of registered courses.  
	 */
	@Override
	public void deleteCourse(ArrayList<Course> courses, ArrayList<Student> students) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter course name:");
		String courseName = scn.nextLine();
		System.out.println("Enter section:");
		int section = Integer.parseInt(scn.nextLine());
		Course deleteThis = null;
		for (Course course : courses) {
			if( course.getName().equalsIgnoreCase(courseName) && section == course.getSection()) {
				deleteThis = course;
				break;
			}
		}
		//remove course from system
		courses.remove(deleteThis);
	
		//remove course from students
		for (Student student : students) {
			for (Course x : student.getCourses()) {
				if (x.getName().equalsIgnoreCase(courseName) && section == x.getSection()) {
					deleteThis = x;
				}
			}
			student.removeCourse(deleteThis);
		}
		
		
	}
	
	/**
	 * 9
	 * this allows the admin to modify instance variables of an instant of a course
	 */
	@Override
	public void editCourse(ArrayList<Course> courses) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter course name:");
		String courseName = scn.nextLine();
		System.out.println("Enter section:");
		int section = Integer.parseInt(scn.nextLine());
		for (Course course : courses) {
			if( course.getName().equals(courseName) && section == course.getSection()) {
				System.out.println("Course name: "+ course.getName());
				System.out.println("Course ID: " + course.getId());
				System.out.println("Instructor: " + course.getInstructor());
				System.out.println("Edit\n 1) course name\n 2) course ID\n 3) instructor \n Enter a number:");
				int edit = Integer.parseInt(scn.nextLine());
				if (edit == 1) {
					System.out.println("Enter new name:");
					String newName = scn.nextLine();
					course.setName(newName);
				}
				else if (edit == 2) {
					System.out.println("Enter new course ID:");
					String id = scn.nextLine();
					course.setId(id);
				}
				else if (edit == 3) {
					System.out.println("Enter new instructor name:");
					String ins = scn.nextLine();
					course.setInstructor(ins);
				}
			}
		}
	}
	
	/**
	 * 10
	 * this allows admin to view a course in detail
	 */
	@Override
	public void viewOne(ArrayList<Course> courses) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter course ID:");
		String id = scn.nextLine();
		for (Course course: courses) {
			if (course.getId().equals(id)) {
				System.out.println("Course name: "+ course.getName());
				System.out.println("Course ID: " + course.getId());
				System.out.println("Current no. of students: " + course.getCurrent());
				System.out.println("Max no. of students: " + course.getMax());
				System.out.println("Students registered: ");
				course.printStudentNames();
				System.out.println("Instructor: " + course.getInstructor());
				System.out.println("Section: " + course.getSection());
				System.out.println("Location: " + course.getLocation());
			}
		}
	}
	
	
	/**
	 * 11
	 * this method instantiates a new student
	 * then adds the new instance to CRS' list of students
	 */
	@Override
	public void addStudent(ArrayList<Student> students) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter student's first name:");
		String firstName = in.nextLine();
		System.out.println("Enter student's last name:");
		String lastName = in.nextLine();
		//instantiate new student
		Student newStudent = new Student(firstName, lastName);
		students.add(newStudent);
		System.out.println(newStudent.getFirst() + " " + newStudent.getLast() + " added as a new student." );
	}

	
	
}
