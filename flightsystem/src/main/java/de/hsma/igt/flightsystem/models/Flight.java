package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "FLIGHT")
public class Flight extends BaseEntity {

	private static final long serialVersionUID = -2213621625495102144L;
	
	@Column
	private String planeType;
	
	@OneToMany(mappedBy = "flight")
	private List<FlightSeats> flightSeats;
	
	@OneToMany(mappedBy = "flight")
	private List<Itinerary> journey;
	
	@OneToMany(mappedBy = "flight",
    		cascade = CascadeType.ALL)
	private Set<Booking> bookings;
	
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

	public Set<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
}
