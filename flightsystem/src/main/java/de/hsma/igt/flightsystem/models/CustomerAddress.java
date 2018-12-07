package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7461527408572140221L;

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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customerID")
	private Customer customer;
	
	public CustomerAddress() {}
	
	public CustomerAddress(String streetname, String streetnumber, String postcode, String state, String country) {
		super();
		this.streetname = streetname;
		this.streetnumber = streetnumber;
		this.postcode = postcode;
		this.state = state;
		this.country = country;
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