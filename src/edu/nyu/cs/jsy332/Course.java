package edu.nyu.cs.jsy332;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import edu.nyu.cs.jsy332.Student;

public class Course implements java.io.Serializable, Comparable<Course>{
	private String name; //0
	private String id; //1
	private int max; //2
	private int current = 0; //3 default no students
	private String instructor; //4
	private int section; //5
	private String location; //6
	private ArrayList<Student> students = new ArrayList<Student>();//7 default empty roster
	
	public Course() {
		
	}
	
	//constructor overloaded
	public Course(String name, String id, int max, String instructor, int section, String location) {
		this.name = name;
		this.id = id;
		this.max = max;
		this.instructor = instructor;
		this.section = section;
		this.location = location;
		

	}
	
	//getter and setter for course name
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//setter and setter for ID
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	//getter for current num of students
	public int getCurrent() {
		return this.current;
	}
	
	//getter for max num of students
	public int getMax() {
		return this.max;
	}
	
	//print students' names in a course
	public void printStudentNames() {
		if (this.students.size() == 0) {
			System.out.println("No registered students");
		}
		else {
			for (Student person : this.students) {
				System.out.println(person.getName());
			}
		}
	}
	
	//getter for list of student objects in a course
	public ArrayList<Student> getStudents() {
		return this.students;
	}
	
	//getter for section number
	public int getSection() {
		return this.section;
	}
	//getter and setter for instructor
	public String getInstructor() {
		return this.instructor;
	}
	public void setInstructor(String x) {
		this.instructor = x;
	}
	
	//getter for location
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * this method checks whether the course is full
	 * if the current number of students must be strictly less than the of max num of students
	 * @return
	 */
	public boolean isNotFull() {
		if (this.getCurrent() < this.getMax()) {
			return true;
		}
		else { //if current >= max
			return false;
		}
	}
	/**
	 * these two methods either adds/removes a student from the student name list for a specific course
	 * @param student
	 */
	public void addStudent(Student student) {
		this.students.add(student);
		this.current ++;
	}
	
	public void removeStudent(Student student) {
		this.students.remove(student);
		this.current --;
	}
	
	@Override
	public int compareTo(Course o) {
		if (this.getCurrent() == o.getCurrent()) {
			return 0;
		}
		else if (this.getCurrent() < o.getCurrent()) {
			return 1;
		}
		else {
			return -1;
		}
	}
	


	

}
