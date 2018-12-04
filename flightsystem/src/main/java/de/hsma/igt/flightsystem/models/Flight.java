package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {

	private static final long serialVersionUID = -2213621625495102144L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer flightID;
	
	@Column
	private String planeType;
	
	@OneToMany(mappedBy = "flight")
	private Set<FlightSeats> flightSeats;
	
	@OneToMany(mappedBy = "itineraryID")
	private Set<Itinerary> journey;
	
	public Flight() {}

	public Integer getFlightID() {
		return flightID;
	}

	public Set<FlightSeats> getFlightSeats() {
		return flightSeats;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setFlightID(Integer flightID) {
		this.flightID = flightID;
	}

	public void setFlightSeats(Set<FlightSeats> flightSeats) {
		this.flightSeats = flightSeats;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public Set<Itinerary> getJourney() {
		return journey;
	}

	public void setJourney(Set<Itinerary> journey) {
		this.journey = journey;
	}
}
