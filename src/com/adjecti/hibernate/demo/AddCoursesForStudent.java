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

public class AddCoursesForStudent {

	public  void enrollStudentInCourses() throws ParseException {

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
			
			//get the student from database
			int id=2;
			Student tempStudent=session.get(Student.class, id);
			System.out.println("Loaded Student "+tempStudent);
			System.out.println("Enrolled Courses"+tempStudent.getCourses());
			
			//create more courses
			Course tempCourse1=new Course("Rubik's Cube");
			Course tempCourse2=new Course("Angular Course");
			
			//add student to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			//save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
						
			//commit transaction
			session.getTransaction().commit();
			System.out.println("reviews added");
		}
		finally {
			session.close();
			factory.close();
		}
	}
}