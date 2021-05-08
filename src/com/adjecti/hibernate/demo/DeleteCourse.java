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

public class DeleteCourse {

	public  void deleteCourse() throws ParseException {

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
			
			int id=10;
			
			Course tempCourse=session.get(Course.class, id);
			System.out.println("deleting the course"+tempCourse);
			session.delete(tempCourse);
			//commit transaction
			session.getTransaction().commit();
		}
		finally {
			session.close();
			factory.close();
		}
	}
}