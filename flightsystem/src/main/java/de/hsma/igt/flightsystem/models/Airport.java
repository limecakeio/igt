package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AIRPORT")
public class Airport extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7718875716371374309L;

	@Column
	private String iataCode;
	@Column
	private String name;
	@Column
	private double longitude;
	@Column
	private double latitude;
	
	public Airport() {}
	 
	public Airport(String iataCode, String name, double latitude, double longitude) {
		super();
		this.iataCode = iataCode;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public String getIataCode() {
		return iataCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
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

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
