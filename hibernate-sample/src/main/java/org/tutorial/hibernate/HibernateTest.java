package org.tutorial.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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
		
		String minVehicleId = "3";
		String vehicleName = "Accord";
		Query query = session.createQuery("from Vehicle where vehicleID > :minVehicleId and vehicleName = :vehicleName");
		query.setInteger("minVehicleId", Integer.parseInt(minVehicleId));
		query.setString("vehicleName", vehicleName);
		
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
