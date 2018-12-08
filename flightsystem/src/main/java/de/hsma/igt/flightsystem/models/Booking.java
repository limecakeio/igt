package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "BOOKING")
public class Booking extends BaseEntity {
	
	private static final long serialVersionUID = -6109445036290765621L;
	
	@ManyToOne()
    @JoinColumn(name = "customerID")
	private Customer customer;
	
	@ManyToOne()
    @JoinColumn(name = "flightID")
	private Flight flight;
	
	public Booking(Customer customer, Flight flight) {
		super();
		this.customer = customer;
		this.flight = flight;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
