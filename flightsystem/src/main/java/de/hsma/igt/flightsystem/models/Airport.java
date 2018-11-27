package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
	public Airport() {}

	public Integer getIataCode() {
		return iataCode;
	}

	public void setIataCode(Integer iataCode) {
		this.iataCode = iataCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
}
