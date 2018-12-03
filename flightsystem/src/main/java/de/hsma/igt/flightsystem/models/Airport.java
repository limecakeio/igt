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
	private String iataCode;
	@Column
	private String name;
	@Column
	private Float longitude;
	@Column
	private Float latitude;
	
	public Airport() {}
	 
	public Airport(String iataCode, String name, Float longitude, Float latitude) {
		super();
		this.iataCode = iataCode;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getIataCode() {
		return iataCode;
	}

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}
}
