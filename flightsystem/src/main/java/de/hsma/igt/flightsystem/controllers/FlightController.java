package de.hsma.igt.flightsystem.controllers;

import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class FlightController extends GenericController<Flight>{

	public FlightController(PersistenceUnit persistenceUnit) {
		super(persistenceUnit, Flight.class);
	}
}
