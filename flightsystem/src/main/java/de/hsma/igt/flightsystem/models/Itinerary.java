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
public class Itinerary extends BaseEntity{
	
	private static final long serialVersionUID = -6109445036290765621L;
	
	@ManyToOne()
    @JoinColumn(name = "flightSegmentID")
	private FlightSegment flightSegment;
	
	@ManyToOne()
    @JoinColumn(name = "flightID")
	private Flight flight;

	@Column
	private Date departureDateTime;
	
	@Column
	private Date arrivalDateTime;
	
	public Itinerary(FlightSegment flightSegment, Flight flight) {
		super();
		this.flightSegment = flightSegment;
		this.flight = flight;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public FlightSegment getFlightSegment() {
		return flightSegment;
	}

	public void setFlightSegment(FlightSegment flightSegment) {
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