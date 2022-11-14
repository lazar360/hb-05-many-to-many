package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCoursesAndStudentsDemo {

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
			
			// create a course 
			Course tempCourse = new Course("Pacman - How to score One Million Points");
			
			// save the course ... and leverage the cascade all
			System.out.println("Saving the course");
			session.save(tempCourse);
			System.out.println("Saved the course : " + tempCourse);
			
			// create the students
			Student tempStudent1 = new Student("John", "Doe", "johndoe@lovetocode.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
			
			// add the students to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			// save the students
			System.out.println("\n Saving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students : " + tempCourse.getStudents());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done !");
			
		} finally {
			session.close();
			
			factory.close();
		}
		
	}

}
