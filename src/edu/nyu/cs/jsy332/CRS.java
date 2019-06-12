package edu.nyu.cs.jsy332;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CRS implements java.io.Serializable{
	
	
	
	/**
	 * THE MAIN METHOD
	 * @param args
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welcome to the Course Registration System");
		//new array-list of courses and students
		ArrayList<Course> courses = null;
		ArrayList<Student> students = new ArrayList<Student>();
		
		//this is just for resetting the system for testing - ignore this please 
/**
		try {
				FileOutputStream fos = new FileOutputStream("Students.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(students);
				oos.reset();
				fos.close();
				oos.close();
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
		
		try {
			FileOutputStream fos = new FileOutputStream("Courses.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(courses);
			oos.reset();
			fos.close();
			oos.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
*/
		//deserializing
		try {
			FileInputStream fis = new FileInputStream("Courses.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			courses = (ArrayList<Course>) ois.readObject();
			ois.close();
			fis.close();

		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}
		
		try {
			FileInputStream fis = new FileInputStream("Students.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			students = (ArrayList<Student>) ois.readObject();
			ois.close();
			fis.close();

		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}
		catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		}

				
		//run through MyUniversityCourses if CRS is being run for the first time
		if (courses == null) {
			courses = new ArrayList<Course>();
			Scanner in = new Scanner(new File("src/MyUniversityCourses.csv"));
			in.nextLine(); //skip first line
			while(in.hasNextLine()) {
				String line = in.nextLine();
				String[] l = line.split(",");
				Course course = new Course(l[0], l[1], Integer.parseInt(l[2]), l[5], Integer.parseInt(l[6]), l[7]);
				courses.add(course);
			}
			in.close();
			students = new ArrayList<Student>();//just an empty list
		}
	
		
		//log-in
		//student username: first name, password: last name
		//admin username: Admin, password: Admin001
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter username:");
		String username = scn.nextLine();
		System.out.println("Enter password:");
		String password = scn.nextLine();
		
		
		//the user is a student:
		if (username.equals(Student.username) && password.equals(Student.password)) {
			Student student = new Student();
			System.out.println("Enter first name:");
			String first = scn.nextLine();
			System.out.println("Enter last name:");
			String last = scn.nextLine();
			//check who the student is
			for (Student someone : students) {
				//System.out.println(someone.getFirst());
				//System.out.println(someone.getLast());
				//System.out.println(someone.getFirst().equals(first) && someone.getLast().equals(last));
				if (first.equals(someone.getFirst()) && last.equals(someone.getLast())) {
					student = someone;
					student.enter();
					break;
				}
				else {
					student.exit();
				}
			}

			//keep running until user exits
			while(student.getKeepGoing()) {
				System.out.println("1) View all available courses");
				System.out.println("2) View all courses that are not full");
				System.out.println("3) Register on a course");
				System.out.println("4) Withdraw from a course");
				System.out.println("5) View all registered courses");
				System.out.println("6) Exit");
				System.out.println("Enter a number");
				int number = Integer.parseInt(scn.nextLine());
				if (number == 1) {
					student.viewAllCourses(courses);
				}
				else if (number == 2) {
					student.viewNotFull(courses);
				} 
				else if (number == 3) {
					student.register(courses);
				} 
				else if (number == 4) {
					student.withdraw(courses);
				} 
				else if (number == 5) {
					student.viewRegistered();
				} 
				else if (number == 6) {
					student.exit();
				}
			}
		}

	
		//user is admin
		else if(username.equals(Admin.username) && password.equals(Admin.password)) {
			Admin admin = new Admin();
			while(admin.getKeepGoing()) {
				//display menu
				System.out.println("Reports:");
				System.out.println("1) View all available courses");
				System.out.println("2) View all courses that are full");
				System.out.println("3) Write to a file the list of courses that are full");
				System.out.println("4) View the names of students registered in a specific course");
				System.out.println("5) View list of courses that a given student is registered in");
				System.out.println("6) Sort courses based on the current number of students registered");
				System.out.println("");
				System.out.println("Management:");
				System.out.println("7) Create a new course");
				System.out.println("8) Delete a course");
				System.out.println("9) Edit a course");
				System.out.println("10) Display information for a given course");
				System.out.println("11) Register a student");
				System.out.println("");
				System.out.println("12) Exit");
				System.out.println("Enter a number:");
				
				int num = scn.nextInt();

				if (num == 1) {
					admin.viewAllCourses(courses);
				}
				else if (num == 2) {
					admin.viewFull(courses);
				}
				else if (num == 3) {
					admin.writeFull(courses);
				}
				else if (num == 4) {
					admin.viewStudentsInCourse(courses);
				}
				else if (num == 5) {
					admin.viewCourseForStudent(students);
				}
				else if (num == 6) {
					admin.sortCourses(courses);
				}
				else if (num == 7) {
					admin.createCourse(courses);
				}
				else if (num == 8) {
					admin.deleteCourse(courses, students);
				}
				else if (num == 9) {
					admin.editCourse(courses);	
				}
				else if (num == 10) {
					admin.viewOne(courses);		
				}
				else if (num == 11) {
					admin.addStudent(students);
				}
				else if (num == 12) {
					admin.exit();
				}
				
			}
		}
		
		//serialize students and courses
		try {
			FileOutputStream fos = new FileOutputStream("Students.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(students);
			fos.close();
			oos.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("Courses.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(courses);
			fos.close();
			oos.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

		}
		
	}
}
