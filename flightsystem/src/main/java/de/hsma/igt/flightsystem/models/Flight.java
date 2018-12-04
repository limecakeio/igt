package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {

	private static final long serialVersionUID = -2213621625495102144L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flightID;
	
	@Column
	private String planeType;
	
	@OneToMany(mappedBy = "flight")
	private ArrayList<FlightSeats> flightSeats;
	
	@OneToMany(mappedBy = "itineraryID")
	private ArrayList<Itinerary> journey;
	
	public Flight() {}

	public int getFlightID() {
		return flightID;
	}

	public ArrayList<FlightSeats> getFlightSeats() {
		return flightSeats;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setFlightID(int flightID) {
		this.flightID = flightID;
	}

	public void setFlightSeats(ArrayList<FlightSeats> flightSeats) {
		this.flightSeats = flightSeats;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public ArrayList<Itinerary> getJourney() {
		return journey;
	}

	public void setJourney(ArrayList<Itinerary> journey) {
		this.journey = journey;
	}
}
