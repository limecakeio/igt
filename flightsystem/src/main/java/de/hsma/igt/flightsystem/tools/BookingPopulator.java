package de.hsma.igt.flightsystem.tools;

import java.util.ArrayList;
import java.util.List;

import de.hsma.igt.flightsystem.models.Booking;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.Flight;

public class BookingPopulator {

	public static List<Booking> populateAsList(List<Customer> customers, List<Flight> flights, int flightsPerCustomer) {
		List<Booking> bookingList = new ArrayList<>();
		for (Customer customer : customers) {
			for (int i = 0; i<flightsPerCustomer; i++) {
				Booking booking = new Booking(customer, flights.get((int)(Math.random()*flights.size())));
				bookingList.add(booking);
			}
		}
		return bookingList;
	}
}
