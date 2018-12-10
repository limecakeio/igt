package de.hsma.igt.flightsystem.controllers;

import de.hsma.igt.flightsystem.models.Booking;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class BookingController extends GenericController<Booking> {

	public BookingController(PersistenceUnit persistenceUnit) {
		super(persistenceUnit, Booking.class);
	}
}
