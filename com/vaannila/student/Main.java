package com.vaannila.student;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vaannila.util.HibernateUtil;

public class Main {
	public static Session session = HibernateUtil.getSessionFactory().openSession();
	public static void main(String[] args) {
		Address address = new Address("OMR Road", "Chennai", "TN", "600097");
		Address address2 = new Address("Mugalivakkam,Sri Lakshmi Nagar", "Chennai", "TN", "600089");
		Student student1 = new Student("Eswar", address);
		Student student2 = new Student("MohammedSardar", address2);		
		System.out.println("Session created");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			// By using cascade=all option the address need not be saved
			// explicitly when the student object is persisted the address will
			// be automatically saved.			
			session.save(student1);
			session.save(student2);		
//			session.save(address);
			
			String hql = "FROM Student AS STU ";
			/*String hqlAdd = "FROM Address AS ADD";*/
			Query query = session.createQuery(hql);
			/*query.setParameter("studentId", 2);
			WHERE STU.studentId = :studentId*/
			/*Query queryAdd = session.createQuery(hqlAdd);		*/
			List<Student> results = query.list();
			/*List<Address> resultAddress = queryAdd.list();	*/		
			for(Student stu : results) {
				System.out.println("Roll Number: " + stu.getStudentId() + ", " + "Student Name: " + stu.getStudentName());
			}
			
			/*for(Address add: resultAddress) {
				System.out.println(
						" Street: " + add.getStreet() + ", " +
						" Student City: " + add.getCity() +
						" State:" + add.getState() +
						" PinCode:" + add.getZipcode());
			}*/
			
			transaction.commit();
			System.out.println("Session created");
			
		} catch (HibernateException e) {
			System.out.println("Session closed on Exception");
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}	
}
