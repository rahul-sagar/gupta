package com.adjecti.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adjecti.hibernate.entity.Course;
import com.adjecti.hibernate.entity.Instructor;
import com.adjecti.hibernate.entity.InstructorDetail;
import com.adjecti.hibernate.entity.Review;
import com.adjecti.hibernate.entity.Student;

public class CreateCourseAndStudentsDemo {

	public  void addCourseAndStudents() throws ParseException {

		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		Session session=factory.getCurrentSession();

		try {

			//start a transaction
			session.beginTransaction();

			//create a course
			Course course=new Course("Pacman - how to score 1 million points");
			
			//save the course 
			System.out.println("Saving the course "+course);
			session.save(course);
			
			//create student
			Student tempStudent1=new Student("John", "Doe", "john@luv2code.com");
			Student tempStudent2=new Student("John", "Doe", "john@luv2code.com");
			
			//add student to course
			course.addStudent(tempStudent1);
			course.addStudent(tempStudent2);

			
			//save the students

			System.out.println("Saving the students "+course.getStudents());			
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved Students: "+course.getStudents());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Course and students  added");
		}
		finally {
			session.close();
			factory.close();
		}
	}
}