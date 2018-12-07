package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6109445036290765621L;
	
	@Column
	private String firstname;
	@Column
	private String lastname;
	@Column
	private String email;
	@Column
	private Date dateOfBirth;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY, optional = false)
	private CustomerAddress address;

	@Enumerated(EnumType.STRING)
	private CustomerStatus status = CustomerStatus.NONE;

    @OneToMany(mappedBy = "customer",
    		cascade = CascadeType.ALL,
    		orphanRemoval = true)
	private Set<CustomerPhone> contactNumbers;
    
	public Customer() {
	}
	public Customer(String firstname, String lastname, String email, Date dateOfBirth) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.status = CustomerStatus.NONE;																																																																																																																																																																																																																																																																																																																																					
	}

	public CustomerAddress getAddress() {
		return address;
	}

	public Set<CustomerPhone> getContactNumbers() {
		return contactNumbers;
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

	public CustomerStatus getStatus() {
		return status;
	}

	public void setAddress(CustomerAddress address) {
		this.address = address;
	}

	public void setContactNumbers(Set<CustomerPhone> contactNumbers) {
		this.contactNumbers = contactNumbers;
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
	
	public void setStatus(CustomerStatus status) {
		this.status = status;
	}
}