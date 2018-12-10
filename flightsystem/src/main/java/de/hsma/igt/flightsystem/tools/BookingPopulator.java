package de.hsma.igt.flightsystem.tools;

import java.util.ArrayList;
import java.util.List;

import de.hsma.igt.flightsystem.models.Booking;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.CustomerStatus;
import de.hsma.igt.flightsystem.models.Flight;

public class BookingPopulator {

	public static List<Booking> populateAsList(List<Customer> customers, List<Flight> flights, int customerPerFlight) {
		List<Booking> bookingList = new ArrayList<>();
		for (Flight flight : flights) {
			for (int i = 0; i<customerPerFlight; i++) {
				Booking booking = new Booking(customers.get((int)(Math.random()*customers.size())), flight);
				bookingList.add(booking);
			}
		}
		return bookingList;
	}
}
