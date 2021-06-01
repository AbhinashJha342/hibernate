package org.tutorial.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.tutorial.hibernate.model.Address;
import org.tutorial.hibernate.model.UserDetails;
import org.tutorial.hibernate.model.Vehicle;

public class HibernateTest {

	public static void main(String[] args) {
		UserDetails newUser = new UserDetails();
		newUser.setUserName("Abhinash");
		Address address = new Address();
		address.setCity("Bokaro");
		address.setPinCode("827013");
		Address address2 = new Address();
		address2.setCity("Kolkata");
		address2.setPinCode("03410");
		newUser.getUserAddresses().add(address);
		newUser.getUserAddresses().add(address2);
		Vehicle myVehicle = new Vehicle();
		myVehicle.setVehicleName("Motorcycle");
		
		//creating hibernate session factory
		Configuration config = new Configuration();
		config.configure();
		// local SessionFactory bean created
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		//before saving data in db, we need to open a transaction
		session.beginTransaction();
		//Query query = session.createQuery("from Vehicle where vehicleID > :minVehicleId and vehicleName = :vehicleName");
		//query.setInteger("minVehicleId", Integer.parseInt(minVehicleId));
		//query.setString("vehicleName", vehicleName);
		session.save(newUser);
		Query query = session.getNamedQuery("UserDetails.byID");
		query.setInteger(0,1);
		
		List<UserDetails> userDeatils = (List<UserDetails>)query.list();
		for(UserDetails user : userDeatils) {
			System.out.println(user.getUserId());
		}
		
		Query query1 = session.getNamedQuery("UserDetails.byName");
		query1.setString(0, "Abhinash");
		
		List<UserDetails> userDeatils1 = (List<UserDetails>)query1.list();
		for(UserDetails user : userDeatils1) {
			System.out.println(user.getUserName());
		}
		
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
