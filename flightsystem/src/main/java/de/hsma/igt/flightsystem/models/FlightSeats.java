package de.hsma.igt.flightsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer FlightSeatID;
	
	@Column
	private Integer count;
	
	@Column
	private Integer price;
	
	@ManyToOne()
    @JoinColumn(name = "flightID")
	private Flight flight;
	
	@ManyToOne()
    @JoinColumn(name = "seatTypeID")
	private SeatType seatType;

	public FlightSeats() {}
	
	public FlightSeats(Integer count, Integer price, Flight flight, SeatType seatType) {
		super();
		this.count = count;
		this.price = price;
		this.flight = flight;
		this.seatType = seatType;
	}

	public Integer getCount() {
		return count;
	}

	public Flight getFlight() {
		return flight;
	}

	public Integer getFlightSeatID() {
		return FlightSeatID;
	}

	public Integer getPrice() {
		return price;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setFlightSeatID(Integer flightSeatID) {
		FlightSeatID = flightSeatID;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}
}
