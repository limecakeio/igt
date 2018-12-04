package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
