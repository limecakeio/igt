package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_PHONE")
public class CustomerPhone implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -900068554945708639L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerPhoneID;
	
	@Column
	private String contactNumber;
	
	@ManyToOne()
    @JoinColumn(name = "customerID")
	private Customer customer;
	
	@Enumerated(EnumType.STRING)
	private PhoneType phoneType;

	public CustomerPhone() {}
	
	public CustomerPhone(Customer customer, String contactNumber, PhoneType phoneType) {
		this.customer = customer;
		this.contactNumber = contactNumber;
		this.phoneType = phoneType;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public PhoneType getPhoneType() {
		return phoneType;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}

	public Integer getCustomerPhoneID() {
		return customerPhoneID;
	}

	public void setCustomerPhoneID(Integer customerPhoneID) {
		this.customerPhoneID = customerPhoneID;
	}
}