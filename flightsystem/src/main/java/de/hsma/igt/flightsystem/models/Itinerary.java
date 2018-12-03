package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Itinerary implements Serializable{
	
	private static final long serialVersionUID = -6109445036290765621L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flightFlightSegmentID;
	
	@ManyToOne()
    @JoinColumn(name = "flightSegmentID")
	private Flightsegment flightSegment;
	
	@ManyToOne()
    @JoinColumn(name = "flightID")
	private Flight flight;
	
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
}