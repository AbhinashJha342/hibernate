package org.tutorial.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bike") // after using @DiscriminatorColumn in base class, we use this in subclasses to set the name for the overridden dType column
public class TwoWheeler extends Vehicle {

	private String steeringhandle;

	public String getSteeringhandle() {
		return steeringhandle;
	}

	public void setSteeringhandle(String steeringhandle) {
		this.steeringhandle = steeringhandle;
	}
	
}
