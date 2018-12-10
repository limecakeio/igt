package de.hsma.igt.flightsystem.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import de.hsma.igt.flightsystem.controllers.*;
import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.Booking;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.models.FlightSegment;
import de.hsma.igt.flightsystem.models.Itinerary;
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
    private static PersistenceUnit persistenceUnit = TestsHelper.resolvePersistenceUnit();
    
	private AirportController ac;
	private BookingController bc; 
	private CustomerController cc;
	private ItineraryController ic;
	private FlightController fc;
	private FlightSegmentController fsc;
	
	long startTimestamp;
	
	private final String CMD_ARGUMENT = "db";
	
	@BeforeClass
	public void setup() {
		ac = new AirportController(persistenceUnit);
		bc = new BookingController(persistenceUnit);
		cc = new CustomerController(persistenceUnit);
		ic = new ItineraryController(persistenceUnit);
		fc = new FlightController(persistenceUnit);
		fsc = new FlightSegmentController(persistenceUnit);
		
		GenericController[] controllers = {
				bc, cc, fc, ic, fsc, ac
				};
		
		for(GenericController c : controllers)
			c.deleteObjects(c.readObjects());
		
	}

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
		
		List<Customer> customers = cc.readObjects();
		List<Flight> flights = fc.readObjects();
		List<Booking> initBooking = bc.readObjects();
		
		List<Booking> bookings = BookingPopulator.populateAsList(customers, flights, nBooking);
		bc.createObjects(bookings);
		
		assertEquals(initBooking.size() + (customers.size() * nBooking), bc.readObjects().size());
		
	}
	
//	@Test
//	public void deleteAirport() {
//		
//		List<Airport> airports = ac.readObjects();
//		List<Flight> flights = fc.readObjects();
//		List<Booking> bookings = bc.readObjects();
//		List<FlightSegment> flightSegments = fsc.readObjects();
//		List<Itinerary> itineraries = ic.readObjects();
//		
//		int deleteIndex = (int)(Math.random()*airports.size());
//		Airport dAirport = airports.get(deleteIndex);
//		
//		List<Flight> dFlights = fc.readByAirport(dAirport);
//		List<FlightSegment> dFlightSegemts = fsc.readByAirport(dAirport);
//		List<Booking> dBookings = bc.readByAirport(dAirport);
//		List<Itinerary> dIt = ic.readByAirport(dAirport);
//		
//		List dList = new ArrayList();
//		dList.add(dAirport);
//		ac.deleteObjects(dList);
//		
//		assertEquals(airports.size() - 1, ac.readObjects().size());
//		assertEquals(flights.size() - dFlights.size(), fc.readObjects());
//		assertEquals(bookings.size() - dBookings.size(), bc.readObjects().size());
//		assertEquals(itineraries.size() - dIt.size(), ic.readObjects().size());
//		
//		
//	}
	
	@Test
	public void deleteFlight() {
		
//		AirportController ac = new AirportController(persistenceUnit);
//		FlightController fc = new FlightController(persistenceUnit);
//		BookingController bc = new BookingController(persistenceUnit);
//		FlightSegmentController fsc = new FlightSegmentController(persistenceUnit);
//		ItineraryController ic = new ItineraryController(persistenceUnit);
		
		List<Airport> airports = ac.readObjects();
		List<Flight> flights = fc.readObjects();
		List<Booking> bookings = bc.readObjects();
		List<FlightSegment> flightSegments = fsc.readObjects();
		List<Itinerary> itineraries = ic.readObjects();

		int deleteIndex = (int)(Math.random()*flights.size());
		Flight dFlight = flights.get(deleteIndex);
		
		List<Booking> dBookings = bc.readByFlight(dFlight);
		
		List dList = new ArrayList();
		dList.add(dFlight);
		fc.deleteObjects(dList);
		
		System.out.println("Bookings: " + bookings.size() + " | " + bc.readObjects().size());
	}
	

}
