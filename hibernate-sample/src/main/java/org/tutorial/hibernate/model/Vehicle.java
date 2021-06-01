package org.tutorial.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


//@NamedNativeQuery(name="Vehicle.byName", query="select * from Vehicle where vehiclename=?0", resultClass=Vehicle.class) // as we using the native query so we need to specify to which class should the query result be mapped to
@Entity
@Table(name = "VEHICLE")
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)	// added for T17 and T18 for inheritance
@DiscriminatorColumn(name="VEHICLE_TYPE", discriminatorType=DiscriminatorType.STRING) // to change the name for auto-generated dType column.
@Inheritance(strategy=InheritanceType.JOINED) // we use this strategy to create a new table and remove any extra null values that we get by simple TABLE_PER_CLASS strategy
public class Vehicle {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vehicleID;
	private String vehicleName;
	//@ManyToOne
	//@JoinColumn(name="USER_ID") //rather than creating a new table, it will use the User_ID of User and create a column for that.
	//private UserDetails userDetails; //user is a reserved keyword, and should not used. error: Error executing DDL "alter table VEHICLE drop constraint FKq9acqfnl2hkuhrm1la9gamh8f" via JDBC Statement
	//@ManyToMany(cascade=CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name="USER_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	private UserDetails user;
	
	@ManyToMany(mappedBy = "vehicles") // to not create 2 tables
	private Collection<UserDetails> userList = new ArrayList<UserDetails>();
	
	public long getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(Integer vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public Collection<UserDetails> getUserList() {
		return userList;
	}
	public void setUserList(Collection<UserDetails> userList) {
		this.userList = userList;
	}
	public UserDetails getUser() {
		return user;
	}
	public void setUser(UserDetails user) {
		this.user = user;
	}
}
