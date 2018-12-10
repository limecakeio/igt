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

import com.google.common.collect.Lists;



//@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProtocoll {
    @Rule public TestName name = new TestName();
    private static PersistenceUnit persistenceUnit = TestsHelper.resolvePersistenceUnit();
    
	private static AirportController ac;
	private static BookingController bc; 
	private static CustomerController cc;
	private static ItineraryController ic;
	private static FlightController fc;
	private static FlightSegmentController fsc;
	
	long startTimestamp;
	
	private final String CMD_ARGUMENT = "db";
	
	@BeforeClass
	public static void setup() {
		ac = new AirportController(persistenceUnit);
		bc = new BookingController(persistenceUnit);
		cc = new CustomerController(persistenceUnit);
		ic = new ItineraryController(persistenceUnit);
		fc = new FlightController(persistenceUnit);
		fsc = new FlightSegmentController(persistenceUnit);
		
//		GenericController[] controllers = {
//				bc, cc, fc, ic, fsc, ac
//				};
//		
//		for(GenericController c : controllers) {
//			c.deleteObjects(c.readObjects());
//			assertEquals(0, c.readObjects().size());
//		}
		
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
	public void test1_populateDatabase() {
		
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
	public void test2_simulateBookings() {
		
		final int nBooking = 5;
		
		List<Customer> customers = cc.readObjects();
		List<Flight> flights = fc.readObjects();
		List<Booking> initBooking = bc.readObjects();
		
		List<Booking> bookings = BookingPopulator.populateAsList(customers, flights, nBooking);
		bc.createObjects(bookings);
		
		assertEquals(initBooking.size() + (customers.size() * nBooking), bc.readObjects().size());
	}
	
	@Test
	public void test3_deleteFlight() {
		
		List<Flight> flights = fc.readObjects();
		List<Booking> bookings = bc.readObjects();

		Flight dFlight = flights.get(0);
		
		List<Booking> dBookings = bc.readByFlight(dFlight);
		
		fc.deleteObjects(Lists.newArrayList(dFlight));
		
		assertEquals(bookings.size() - dBookings.size(), bc.readObjects().size());
	}
}
