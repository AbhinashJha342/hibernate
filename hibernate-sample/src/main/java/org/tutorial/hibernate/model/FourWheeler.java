package org.tutorial.hibernate.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "FOUR_WHEELER")
//@DiscriminatorValue("Car")
public class FourWheeler extends Vehicle {

	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
