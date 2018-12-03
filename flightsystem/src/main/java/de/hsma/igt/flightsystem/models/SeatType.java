package de.hsma.igt.flightsystem.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SEAT_TYPE")
public class SeatType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5993496587132716669L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seatTypeID;
	@Column
	private String seatTypeName;
	@OneToMany(mappedBy = "seatType")
	private Set<FlightSeats> flightSeats;
	
	public SeatType() {}
	
	public SeatType(String seatTypeName) {
		super();
		this.seatTypeName = seatTypeName;
	}

	public Integer getSeatTypeID() {
		return seatTypeID;
	}

	public String getSeatTypeName() {
		return seatTypeName;
	}

	public void setSeatTypeID(Integer seatTypeID) {
		this.seatTypeID = seatTypeID;
	}

	public void setSeatTypeName(String seatTypeName) {
		this.seatTypeName = seatTypeName;
	}
}
