package de.hsma.igt.flightsystem.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.models.FlightSeats;
import de.hsma.igt.flightsystem.models.SeatType;
import de.hsma.igt.flightsystem.tools.RandomGenerator;

public class FlightPopulator {
	
	public static List<Flight> populateFlightsAsList(int numberOfCustomers) {
		
		List<Flight> flights = new ArrayList<Flight>();
		
		for (int i = 0; i < numberOfCustomers; i++) {
			Flight flight = new Flight();
			String planeType = RandomGenerator.getRandomAString(100);
			FlightSeats flightSeatsEconomy = new FlightSeats(50, 50, flight, SeatType.ECONOMY);
			FlightSeats flightSeatsBusiness = new FlightSeats(50, 50, flight, SeatType.BUSINESS);
			
			List<FlightSeats> list = Arrays.asList(flightSeatsBusiness, flightSeatsEconomy);
			Set<FlightSeats> flightSeats = new HashSet<>(list);
			
			
			flights.add(flight);
		}
		
		return flights;
	}

}
