package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AIRPORT")
public class Airport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5599306893398434798L;

	@Id
	private Integer iataCode;
	@Column
	private String name;
	@Column
	private Float longitude;
	@Column
	private Float latitude;
	@OneToMany(mappedBy = "destination")
	private Set<Flightsegment> arrivingFlightSegments;
	
	@OneToMany(mappedBy = "origin")
	private Set<Flightsegment> leavingFlightSegments;
	
	public Airport() {}
	 
	public Airport(Integer iataCode, String name, Float longitude, Float latitude) {
		super();
		this.iataCode = iataCode;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public Set<Flightsegment> getArrivingFlightSegments() {
		return arrivingFlightSegments;
	}

	public Integer getIataCode() {
		return iataCode;
	}

	public Float getLatitude() {
		return latitude;
	}

	public Set<Flightsegment> getLeavingFlightSegments() {
		return leavingFlightSegments;
	}

	public Float getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public void setArrivingFlightSegments(Set<Flightsegment> arrivingFlightSegments) {
		this.arrivingFlightSegments = arrivingFlightSegments;
	}

	public void setIataCode(Integer iataCode) {
		this.iataCode = iataCode;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public void setLeavingFlightSegments(Set<Flightsegment> leavingFlightSegments) {
		this.leavingFlightSegments = leavingFlightSegments;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}
}
