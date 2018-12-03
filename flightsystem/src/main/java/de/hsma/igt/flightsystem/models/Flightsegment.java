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
	@ManyToMany(mappedBy = "flightsegments")
	private Set<Flight> flights;
	@ManyToOne(optional=false)
    @JoinColumn(name = "destination_iataCode", referencedColumnName="iataCode")
	private Airport destination;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "origin_iataCode", referencedColumnName="iataCode")
	private Airport origin;
	
	private Flightsegment() {
	}

	public Flightsegment(String flightName, Integer distanceInMiles) {
		super();
		this.flightName = flightName;
		this.distanceInMiles = distanceInMiles;
	}

	public Airport getDestination() {
		return destination;
	}

	public Integer getDistanceInMiles() {
		return distanceInMiles;
	}

	public String getFlightName() {
		return flightName;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public Integer getFlightSegmentID() {
		return flightSegmentID;
	}

	public Airport getOrigin() {
		return origin;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	public void setDistanceInMiles(Integer distanceInMiles) {
		this.distanceInMiles = distanceInMiles;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}

	public void setFlightSegmentID(Integer flightSegmentID) {
		this.flightSegmentID = flightSegmentID;
	}

	public void setOrigin(Airport origin) {
		this.origin = origin;
	}
}
