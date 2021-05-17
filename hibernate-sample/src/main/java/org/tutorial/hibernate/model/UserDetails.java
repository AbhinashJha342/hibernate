package org.tutorial.hibernate.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="USER_DETAILS")
public class UserDetails {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) //default column name= UserDetails_userId
	private int userId;
	//@EmbeddedId
	//private LoginName userId;
	private String userName;
	@Temporal(TemporalType.DATE)
	private Date joinedDate;
	@Lob
	private String description;
	@ElementCollection(fetch=FetchType.EAGER)// default table name generated- UserDetails_userAddresses
	@JoinTable(name="USER_ADDRESS",
			joinColumns=@JoinColumn(name="USER_ID") //this is to override the foreign key name that comes in user_address table.
			)
	@GenericGenerator(name = "hilo-gen", strategy = "increment")
	@CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "hilo-gen", type = @Type(type = "long"))
	private Collection<Address> userAddresses = new ArrayList<Address>();
	//with hashSet there is no index available, so in the table as well we don't have the indexes.
	//private Set<Address> userAddresses = new HashSet();
	
//	@Embedded
//	private Address homeAddress;
//	
//	@Embedded
//	@AttributeOverrides({
//		@AttributeOverride(name="street", column=@Column(name="HOME_STRRET_NAME")),
//		@AttributeOverride(name="city", column=@Column(name="HOME_CITY_NAME")),
//		@AttributeOverride(name="state", column=@Column(name="HOME_STATE_NAME")),
//		@AttributeOverride(name="pinCode", column=@Column(name="HOME_PINCODE")),
//	})
//	private Address officeAddress;
	
	//@OneToOne(cascade = {CascadeType.ALL})
	//private Vehicle vehicle;
	
	//@OneToMany(mappedBy = "userDetails") // we don't want a separate table. we provide the same variable name which is mapped by @ManyToOne in the other class. 
	@ManyToMany(cascade=CascadeType.ALL) // to avoid error- object references an unsaved transient instance - save the transient instance before flushing
	@JoinColumn(name="VEHICLE_ID")
	private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	public Collection<Address> getUserAddresses() {
		return userAddresses;
	}
	public void setUserAddresses(Collection<Address> userAddresses) {
		this.userAddresses = userAddresses;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
}
