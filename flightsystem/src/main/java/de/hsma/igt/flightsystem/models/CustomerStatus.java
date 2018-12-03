package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_STATUS")
public class CustomerStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4259474878759379485L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerStatusID;
	@Column
	private String customerStatusName;
	
	public CustomerStatus() {}

	public CustomerStatus(String customerStatusName) {
		this.customerStatusName = customerStatusName;
	}

	public Integer getCustomerStatusID() {
		return customerStatusID;
	}

	public String getCustomerStatusName() {
		return customerStatusName;
	}

	public void setCustomerStatusID(Integer customerStatusID) {
		this.customerStatusID = customerStatusID;
	}

	public void setCustomerStatusName(String customerStatusName) {
		this.customerStatusName = customerStatusName;
	}
	
	
}
