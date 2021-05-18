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
		
		//Query query = session.createQuery("from Vehicle");
		Query query = session.createQuery("select vehicleID,vehicleName from Vehicle"); // this would return only the column associated with the member variable. 
		//and the list will be a string as the member variable type.
		//pagination
		query.setFirstResult(3); // will start from the 4th index in the table.
		query.setMaxResults(8); // sets the maximum number of data returned
		List<Object[]> myList = (List<Object[]>)query.list();
		for(Object[] vehicle : myList) {
			System.out.println(vehicle[0]+" "+vehicle[1]);
		}
		System.out.println(myList.size());
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
