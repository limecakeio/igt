package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6109445036290765621L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerID;
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String email;
	@Column
	private Date dateOfBirth;
	// OneToOne bidirectional association
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private CustomerAddress address;

	// ManyToOne unidirectional association
	@ManyToOne
	private CustomerStatus status;

	// OneToMany unidirectional association
	@OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerPhone> contactNumbers = new ArrayList<>();

	public List<CustomerPhone> getContactNumbers() {
		return contactNumbers;
	}

	public void setContactNumbers(List<CustomerPhone> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	public Customer() {
	}

	public Customer(String firstname, String lastname, String email, Date dateOfBirth) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public CustomerAddress getAddress() {
		return address;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setAddress(CustomerAddress address) {
		this.address = address;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}
}