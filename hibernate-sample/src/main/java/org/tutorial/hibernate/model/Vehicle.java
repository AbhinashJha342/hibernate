package org.tutorial.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {

	@Id@GeneratedValue(strategy=GenerationType.AUTO)
	private long vehicleID;
	private String vehicleName;
	@ManyToOne
	@JoinColumn(name="USER_ID") //rather than creating a new table, it will use the User_ID of User and create a column for that.
	private UserDetails userDetails; //user is a reserved keyword, and should not used. error: Error executing DDL "alter table VEHICLE drop constraint FKq9acqfnl2hkuhrm1la9gamh8f" via JDBC Statement
	
	public long getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(long vehicleID) {
		this.vehicleID = vehicleID;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
