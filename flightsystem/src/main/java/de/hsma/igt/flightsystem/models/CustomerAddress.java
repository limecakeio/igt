package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7461527408572140221L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressID;
	
	@Column
	private String streetname;
	
	@Column
	private String streetnumber;
	@Column
	private String postcode;
	@Column
	private String state;
	@Column
	private String country;
	
	public CustomerAddress() {}
	
	public CustomerAddress(String streetname, String streetnumber, String postcode, String state, String country) {
		super();
		this.streetname = streetname;
		this.streetnumber = streetnumber;
		this.postcode = postcode;
		this.state = state;
		this.country = country;
	}

	public Integer getAddressID() {
		return addressID;
	}

	public String getCountry() {
		return country;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getState() {
		return state;
	}

	public String getStreetname() {
		return streetname;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}
}