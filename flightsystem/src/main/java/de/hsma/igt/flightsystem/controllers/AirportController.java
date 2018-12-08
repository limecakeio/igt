package de.hsma.igt.flightsystem.controllers;


import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class AirportController extends GenericController<Airport>{

	public AirportController(PersistenceUnit persistenceUnit) {
		super(persistenceUnit, Airport.class);
	}
}
