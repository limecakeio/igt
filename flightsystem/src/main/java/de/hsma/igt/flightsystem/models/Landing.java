package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LANDING")
public class Landing implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer landingID;

	@ManyToOne()
    @JoinColumn(name = "flightSegmentID")
	private FlightSegment flightSegment;
	
	@ManyToOne()
    @JoinColumn(name = "iataCode")
	private Airport airport;
	
	@Column
	private Date time;
	
	public Landing(FlightSegment flightSegment, Airport airport, Date time) {
		super();
		this.flightSegment = flightSegment;
		this.airport = airport;
		this.time = time;
	}
	

	public Airport getAirport() {
		return airport;
	}

	public FlightSegment getFlightSegment() {
		return flightSegment;
	}

	public Date getTime() {
		return time;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public void setFlightSegment(FlightSegment flightSegment) {
		this.flightSegment = flightSegment;
	}

	public void setTime(Date time) {
		this.time = time;
	}
}
