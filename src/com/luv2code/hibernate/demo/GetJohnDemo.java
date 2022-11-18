package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class GetJohnDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session 
		Session session = factory.getCurrentSession();
				
		try {
			// start a transaction
			session.beginTransaction();
			
			// get the student john from the database with id
			//int studentId = 1;
			
			//Student tempStudent = session.get(Student.class, studentId);
			
			//get the student john from the database with lastName
			
			@SuppressWarnings("unchecked")
			List<Student> tempStudent  = session.createQuery("from Student s where" + 
			" s.lastName ='Doe'").getResultList();
			
			System.out.println("Loaded student : " + tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done !");
			
		} finally {
			session.close();
			
			factory.close();
		}
		
	}

}
