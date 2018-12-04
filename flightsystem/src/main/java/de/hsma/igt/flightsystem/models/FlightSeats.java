package de.hsma.igt.flightsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHT_SEATS")
public class FlightSeats {

	private static final long serialVersionUID = -900068554945708639L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FlightSeatID;
	
	@Column
	private int count;
	
	@Column
	private double price;
	
	@ManyToOne()
    @JoinColumn(name = "flightID")
	private Flight flight;
	
	@Enumerated(EnumType.STRING)
	private SeatType seatType;

	public FlightSeats() {}
	
	public FlightSeats(int count, double price, Flight flight, SeatType seatType) {
		super();
		this.count = count;
		this.price = price;
		this.flight = flight;
		this.seatType = seatType;
	}

	public int getCount() {
		return count;
	}

	public Flight getFlight() {
		return flight;
	}

	public Integer getFlightSeatID() {
		return FlightSeatID;
	}

	public double getPrice() {
		return price;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setFlightSeatID(Integer flightSeatID) {
		FlightSeatID = flightSeatID;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
}
