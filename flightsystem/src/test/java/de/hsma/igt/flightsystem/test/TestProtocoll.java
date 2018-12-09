package de.hsma.igt.flightsystem.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import de.hsma.igt.flightsystem.controllers.*;
import de.hsma.igt.flightsystem.controllers.IController;
import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.Booking;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.models.FlightSegment;
import de.hsma.igt.flightsystem.models.TestsHelper;
import de.hsma.igt.flightsystem.tools.AirportPopulator;
import de.hsma.igt.flightsystem.tools.BookingPopulator;
import de.hsma.igt.flightsystem.tools.CustomerPopulator;
import de.hsma.igt.flightsystem.tools.FlightPopulator;
import de.hsma.igt.flightsystem.tools.FlightSegmentPopulator;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;
import de.hsma.igt.flightsystem.tools.Populator;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;



//@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProtocoll {
    @Rule public TestName name = new TestName();
    private PersistenceUnit persistenceUnit = TestsHelper.resolvePersistenceUnit();
	
	long startTimestamp;
	
	private final String CMD_ARGUMENT = "db";

    @Before
    public void beforeEvery() {
        long startTimestamp = System.nanoTime();
    }

    @After
    public void afterEvery() {
        long endTimestamp = System.nanoTime();
        TestsHelper.logTimeTaken(name.getMethodName(), startTimestamp, endTimestamp);
    }


	@Test
	public void populateDatabase() {
		
		final int nRecords = 100;
		
		List<GenericController> controllers = new ArrayList<GenericController>();
		List<Integer> initSizes = new ArrayList<Integer>();
		
		controllers.add(new AirportController(persistenceUnit));
		controllers.add(new CustomerController(persistenceUnit));
		controllers.add(new FlightController(persistenceUnit));
		controllers.add(new FlightSegmentController(persistenceUnit));
		
		GenericController ac = new AirportController(persistenceUnit);
		GenericController cc = new CustomerController(persistenceUnit);
		GenericController fc = new FlightController(persistenceUnit);
		GenericController fsc = new FlightSegmentController(persistenceUnit);
		
		List<Airport> airports = new AirportPopulator().getAirports();
		List<Customer> customers = new CustomerPopulator().populateAsList(nRecords);
		List<Flight> flights = new FlightPopulator().getFlights();
		List<FlightSegment> flightSegments = new FlightSegmentPopulator(airports).getFlightSegments();

		int ai = ac.readObjects().size();
		int ci = cc.readObjects().size();
		int fi = fc.readObjects().size();
		int fsi = fsc.readObjects().size();
		
		ac.createObjects(airports);
		cc.createObjects(customers);
		fc.createObjects(flights);
		fsc.createObjects(flightSegments);

		assertEquals(ac.readObjects().size(), airports.size() + ai);
		assertEquals(cc.readObjects().size(), customers.size() + ci);
		assertEquals(fc.readObjects().size(), flights.size() + fi);
		assertEquals(fsc.readObjects().size(), flightSegments.size() + fsi);
		
	}
	
	@Test
	public void simulateBookings() {
		
		final int nBooking = 5;

		GenericController cc = new CustomerController(persistenceUnit);
		GenericController fc = new FlightController(persistenceUnit);
		GenericController bc = new BookingController(persistenceUnit);
		
		List<Customer> customers = cc.readObjects();
		List<Flight> flights = fc.readObjects();
		List<Booking> initBooking = bc.readObjects();
		
		List<Booking> bookings = new BookingPopulator().populateAsList(customers, flights, nBooking);
		bc.createObjects(bookings);
		
		assertEquals(initBooking.size() + (customers.size() * nBooking), bc.readObjects().size());
		
	}
	
	@Test
	public void deleteAirport() {
		
	}

}
