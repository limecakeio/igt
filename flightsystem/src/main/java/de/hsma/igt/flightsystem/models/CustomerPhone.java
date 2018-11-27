package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_PHONE")
public class CustomerPhone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -900068554945708639L;

	@Id
	private String contactNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
	private Customer customer;

	@OneToMany
	private PhoneType phoneType;

	public CustomerPhone(String contactNumber, Customer customer, PhoneType phoneType) {
		this.contactNumber = contactNumber;
		this.customer = customer;
		this.phoneType = phoneType;
	}
	
	public CustomerPhone() {}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
}
