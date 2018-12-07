package de.hsma.igt.flightsystem.models;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "FLIGHTSEGMENT")
public class FlightSegment extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1088312445291365796L;

	@Column
	private String flightName;

	@Column
	private int distanceInMiles;

	@ManyToOne()
    @JoinColumn(name = "departureAirportID")
	private Airport departureAirport;

	@ManyToOne()
    @JoinColumn(name = "arrivalAirportID")
	private Airport arrivalAirport;


	public FlightSegment(Airport departureAirport, Airport arrivalAirport) {
		super();
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.flightName = departureAirport.getIataCode() + " -> " + arrivalAirport.getIataCode();
		this.distanceInMiles = getDistanceInMiles(departureAirport.getLatitude(), departureAirport.getLongitude(), arrivalAirport.getLatitude(), arrivalAirport.getLongitude());
	}

	public int getDistanceInMiles() {
		return distanceInMiles;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setDistanceInMiles(int distanceInMiles) {
		this.distanceInMiles = distanceInMiles;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

    public static int getDistanceInMiles(double latA, double longA, double latB, double longB) {
        double eRadius = 6371;
        double dLat = Math.toRadians(latB - latA);
        double dLon = Math.toRadians(longB - longA);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(Math.toRadians(latA)) * Math.cos(Math.toRadians(latB)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int)(eRadius * c * 0.621); //0.621 is miles per kilometer
    }
}
