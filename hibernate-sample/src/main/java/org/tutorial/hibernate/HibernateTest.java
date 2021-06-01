package org.tutorial.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
		
		UserDetails newUser1 = new UserDetails();
		newUser1.setUserName("Alicia");
		
		UserDetails newUser2 = new UserDetails();
		newUser2.setUserName("Raman");
		
		//creating hibernate session factory
		Configuration config = new Configuration();
		config.configure();
		// local SessionFactory bean created
		SessionFactory sessionFactory = config.buildSessionFactory();
		
//		Session session1 = sessionFactory.openSession();
//		session1.beginTransaction();
//		session1.save(newUser1);
//		session1.getTransaction().commit();
//		
//		Session session2 = sessionFactory.openSession();
//		session2.beginTransaction();
//		session2.save(newUser2);
//		session2.getTransaction().commit();
		
		
		Session session = sessionFactory.openSession();
		//before saving data in db, we need to open a transaction
		session.beginTransaction();
		//Query query = session.createQuery("from Vehicle where vehicleID > :minVehicleId and vehicleName = :vehicleName");
		//query.setInteger("minVehicleId", Integer.parseInt(minVehicleId));
		//query.setString("vehicleName", vehicleName);
		session.save(newUser);
		session.save(newUser1);
		session.save(newUser2);
		//using Criteria API
		UserDetails exampleUser = new UserDetails();
		exampleUser.setUserName("%A%");
		
		Example example = Example.create(exampleUser).excludeProperty("userName"); // this is for the case when we want to exclude any property. We can chain then using many exclude property.
		Criteria criteria = session.createCriteria(UserDetails.class)
							.add(example);
		
		//this would return a list of UserDtails, but only one element with UserName as Alicia. we can use wildcard here as well.
		
		//once save is done, we need to end the transaction.
		session.getTransaction().commit();
		//ideally below should in finally block.		
		
		/**
		//to get the data from database. we passed 1 which is the primary key.
		
		session.close();
		System.out.println(user.getUserAddresses().size()); //2 before using session.close()
		//after session.close()- Exception in thread "main" org.hibernate.LazyInitializationException:
		 * 
		 */
		
	}

}
