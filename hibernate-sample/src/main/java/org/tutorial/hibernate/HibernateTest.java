package org.tutorial.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.tutorial.hibernate.model.Address;
import org.tutorial.hibernate.model.UserDetails;
import org.tutorial.hibernate.model.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails user = new UserDetails();
		user.setUserName("Abhinash");
		Address address = new Address();
		address.setCity("Bokaro");
		address.setPinCode("827013");
		Address address2 = new Address();
		address2.setCity("Kolkata");
		address2.setPinCode("03410");
		user.getUserAddresses().add(address);
		user.getUserAddresses().add(address2);
		Vehicle myVehicle = new Vehicle();
		myVehicle.setVehicleName("Motorcycle");
		//user.setVehicle(myVehicle);
		//creating hibernate session factory
		Configuration config = new Configuration();
		config.configure();
		// local SessionFactory bean created
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		//before saving data in db, we need to open a transaction
		session.beginTransaction();
		session.save(user);
		//once save is done, we need to end the transaction.
		session.getTransaction().commit();
		//ideally below should in finally block.
		session.close();
		
		user = null;
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		/**
		//to get the data from database. we passed 1 which is the primary key.
		user = session.get(UserDetails.class, 1);
		session.close();
		System.out.println(user.getUserAddresses().size()); //2 before using session.close()
		//after session.close()- Exception in thread "main" org.hibernate.LazyInitializationException:
		 * 
		 */
		
	}

}
