package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213621625495102144L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer flightID;
	@Column
	private String planeType;
	
	public Flight() {}

	public Integer getFlightID() {
		return flightID;
	}

	public void setFlightID(Integer flightID) {
		this.flightID = flightID;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
}
