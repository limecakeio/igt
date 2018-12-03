package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHTSEGMENT")
public class Flightsegment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1088312445291365796L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flightSegmentID;
	@Column
	private String flightName;
	@Column
	private Integer distanceInMiles;
	
	private Flightsegment() {
	}

	public Flightsegment(String flightName, Integer distanceInMiles) {
		super();
		this.flightName = flightName;
		this.distanceInMiles = distanceInMiles;
	}

	public Integer getDistanceInMiles() {
		return distanceInMiles;
	}

	public String getFlightName() {
		return flightName;
	}
	
	public Integer getFlightSegmentID() {
		return flightSegmentID;
	}

	public void setDistanceInMiles(Integer distanceInMiles) {
		this.distanceInMiles = distanceInMiles;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public void setFlightSegmentID(Integer flightSegmentID) {
		this.flightSegmentID = flightSegmentID;
	}
}
