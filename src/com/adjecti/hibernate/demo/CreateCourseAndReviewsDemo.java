package com.adjecti.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.adjecti.hibernate.entity.Course;
import com.adjecti.hibernate.entity.Instructor;
import com.adjecti.hibernate.entity.InstructorDetail;
import com.adjecti.hibernate.entity.Review;

public class CreateCourseAndReviewsDemo {

	public  void addCourseAndReview() throws ParseException {

		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		Session session=factory.getCurrentSession();

		try {

			//start a transaction
			session.beginTransaction();

			//create a course
			Course course=new Course("Pacman - how to score 1 million points");

			//add some reviews
			course.addReview(new Review("Great course, loved it!!"));
			course.addReview(new Review("cool course, well done"));
			course.addReview(new Review("what a dumb course , you are an idiot"));
			
			//save the course and leverage the cascade all:-
			System.out.println("Saving the course "+course);
			System.out.println(course.getReviews());
			session.save(course);
			
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