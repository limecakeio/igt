package de.hsma.igt.flightsystem.controllers;


import de.hsma.igt.flightsystem.models.FlightSegment;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class FlightSegmentController extends GenericController<FlightSegment>{

	public FlightSegmentController(PersistenceUnit persistenceUnit) {
		super(persistenceUnit, FlightSegment.class);
	}
}
