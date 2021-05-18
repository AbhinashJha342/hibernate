package org.tutorial.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.tutorial.hibernate.model.Address;
import org.tutorial.hibernate.model.FourWheeler;
import org.tutorial.hibernate.model.TwoWheeler;
import org.tutorial.hibernate.model.UserDetails;
import org.tutorial.hibernate.model.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		//creating hibernate session factory
		Configuration config = new Configuration();
		config.configure();
		// local SessionFactory bean created
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		//before saving data in db, we need to open a transaction
		session.beginTransaction();
		
		//once save is done, we need to end the transaction.
		session.getTransaction().commit();
		//ideally below should in finally block.
		session.close();
		/**
		//to get the data from database. we passed 1 which is the primary key.
		
		session.close();
		System.out.println(user.getUserAddresses().size()); //2 before using session.close()
		//after session.close()- Exception in thread "main" org.hibernate.LazyInitializationException:
		 * 
		 */
		
	}

}
