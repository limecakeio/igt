package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213621625495102144L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flightID;
	@Column
	private String planeType;
	
	@ManyToMany(cascade = { 
		    CascadeType.PERSIST, 
		    CascadeType.MERGE
		})
		@JoinTable(name = "customer_flight",
		    joinColumns = @JoinColumn(name = "flightID"),
		    inverseJoinColumns = @JoinColumn(name = "customerID")
		)
	private Set<Customer> customer;
	
	@OneToMany(mappedBy = "flight")
	private Set<FlightSeats> flightSeats;
	
	@ManyToMany(cascade = { 
		    CascadeType.PERSIST, 
		    CascadeType.MERGE
		})
		@JoinTable(name = "flight_flightsegment",
		    joinColumns = @JoinColumn(name = "flightID"),
		    inverseJoinColumns = @JoinColumn(name = "flightSegmentID")
		)
	private Set<Flightsegment> flightsegments;
	
	public Flight() {}

	public Set<Customer> getCustomer() {
		return customer;
	}

	public Integer getFlightID() {
		return flightID;
	}

	public Set<FlightSeats> getFlightSeats() {
		return flightSeats;
	}

	public Set<Flightsegment> getFlightsegments() {
		return flightsegments;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setCustomer(Set<Customer> customer) {
		this.customer = customer;
	}

	public void setFlightID(Integer flightID) {
		this.flightID = flightID;
	}

	public void setFlightSeats(Set<FlightSeats> flightSeats) {
		this.flightSeats = flightSeats;
	}

	public void setFlightsegments(Set<Flightsegment> flightsegments) {
		this.flightsegments = flightsegments;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
}
