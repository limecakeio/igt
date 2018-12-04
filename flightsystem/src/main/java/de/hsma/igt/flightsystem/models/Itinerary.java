package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITINERARY")
public class Itinerary implements Serializable{
	
	private static final long serialVersionUID = -6109445036290765621L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itineraryID;
	
	@ManyToOne()
    @JoinColumn(name = "flightSegmentID")
	private Flightsegment flightSegment;
	
	@ManyToOne()
    @JoinColumn(name = "flightID")
	private Flight flight;
	
	@Column
	private Date departureDateTime;
	
	@Column
	private Date arrivalDateTime;
	
	public Itinerary(Flightsegment flightSegment, Flight flight) {
		super();
		this.flightSegment = flightSegment;
		this.flight = flight;
	}

	public Flightsegment getCustomer() {
		return flightSegment;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setCustomer(Flightsegment flightsegment) {
		this.flightSegment = flightsegment;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Flightsegment getFlightSegment() {
		return flightSegment;
	}

	public void setFlightSegment(Flightsegment flightSegment) {
		this.flightSegment = flightSegment;
	}

	public Date getDepartureDateTime() {
		return departureDateTime;
	}

	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Date getArrivalDateTime() {
		return arrivalDateTime;
	}

	public void setArrivalDateTime(Date arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
}