package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Starting implements Serializable{

	public Starting(Flightsegment flightSegment, Airport airport, Date time) {
		super();
		this.flightSegment = flightSegment;
		this.airport = airport;
		this.time = time;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer startingID;
	
	@ManyToOne()
    @JoinColumn(name = "flightSegmentID")
	private Flightsegment flightSegment;
	
	@ManyToOne()
    @JoinColumn(name = "iataCode")
	private Airport airport;
	
	@Column
	private Date time;
	
	public Airport getAirport() {
		return airport;
	}

	public Flightsegment getFlightSegment() {
		return flightSegment;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public void setFlightSegment(Flightsegment flightSegment) {
		this.flightSegment = flightSegment;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}