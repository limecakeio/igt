package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHTSEGMENT")
public class FlightSegment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088312445291365796L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightSegmentID;
	@Column
	private String flightName;

	@Column
	private int distanceInMiles;

	@ManyToOne()
    @JoinColumn(name = "departureAirportID")
	private Airport departureAirport;

	@ManyToOne()
    @JoinColumn(name = "arrivalAirportID")
	private Airport arrivalAirport;

	
	private FlightSegment() {
	}

	public FlightSegment(String flightName, Integer distanceInMiles) {
		super();
		this.flightName = flightName;
		this.distanceInMiles = distanceInMiles;
	}

	public int getDistanceInMiles() {
		return distanceInMiles;
	}

	public String getFlightName() {
		return flightName;
	}
	
	public int getFlightSegmentID() {
		return flightSegmentID;
	}

	public void setDistanceInMiles(int distanceInMiles) {
		this.distanceInMiles = distanceInMiles;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public void setFlightSegmentID(int flightSegmentID) {
		this.flightSegmentID = flightSegmentID;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
}
