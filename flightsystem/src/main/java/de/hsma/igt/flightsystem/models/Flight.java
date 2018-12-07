package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT")
public class Flight extends BaseEntity {

	private static final long serialVersionUID = -2213621625495102144L;
	
	@Column
	private String planeType;
	
	@OneToMany(mappedBy = "flight")
	private List<FlightSeats> flightSeats;
	
	@OneToMany(mappedBy = "flight")
	private List<Itinerary> journey;
	
	public Flight() {}

	public List<FlightSeats> getFlightSeats() {
		return flightSeats;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setFlightSeats(List<FlightSeats> flightSeats) {
		this.flightSeats = flightSeats;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public List<Itinerary> getJourney() {
		return journey;
	}

	public void setJourney(List<Itinerary> journey) {
		this.journey = journey;
	}
}
