package de.hsma.igt.flightsystem.tools;

import java.util.*;

import de.hsma.igt.flightsystem.models.*;

/**
 * Flight Populator
 *
 * Flights are predefined journeys, made up of itineraries
 */

public class FlightPopulator {
    //TODO move to flight controller
    public static final int SEAT_COUNT_BUSINESS = 25;
    public static final double SEAT_PRICE_BUSINESS = 199.95;
    public static final int SEAT_COUNT_ECONOMY = 50;
    public static final double SEAT_PRICE_ECONOMY = 59.95;

    private List<Airport> airports;
    private List<FlightSegment> flightSegments;
    private List<Flight> flights;

    public FlightPopulator() {
        this.flights = new ArrayList();
        this.airports = new AirportPopulator().getAirports();
        this.flightSegments = new FlightSegmentPopulator(this.airports).getFlightSegments();

        this.initFlights();
    }

    private void initFlights() {
        //Direct flights...

        //From Singapore to Manchester direct
        flights.add(this.composeFlight(new String[]{"SIN", "MAN"}));
        //From Helsinki to Krakow
        flights.add(this.composeFlight(new String[]{"HEL", "KRK"}));
        //From Bremen to Sydney
        flights.add(this.composeFlight(new String[]{"BRE", "SYD"}));
        //From Alaska to Kongo
        flights.add(this.composeFlight(new String[]{"DUT", "KOO"}));
        //From Gothenburg to Bergen
        flights.add(this.composeFlight(new String[]{"GOT", "BGO"}));

        //Multi-Stops

        //From Bremen to Singapore to Gothenburg
        flights.add(this.composeFlight(new String[]{"BRE", "SIN", "GOT"}));
        //From Helsinki to Gothenburg to Alaska
        flights.add(this.composeFlight(new String[]{"HEL", "GOT", "DUT"}));
        //From Krakow to Bergen to Manchester to Kongo
        flights.add(this.composeFlight(new String[]{"KRK", "BGO", "KOO"}));
        //From Singapore to Bremen to Kongo to Syndey
        flights.add(this.composeFlight(new String[]{"SIN", "BRE", "KOO", "SYD"}));

        //Round trip
        flights.add(this.composeFlight(new String[]{"SIN", "SYD", "HEL", "GOT", "BGO", "DUT", "MAN", "BRE", "KRK", "KOO", "SIN"}));
    }

    //TODO move to flight controller
    private Flight composeFlight(String[] iatas) {
        Flight flight = new Flight();

        //Set a random plane type for flight
        flight.setPlaneType(RandomGenerator.getRandomAString(5));

        //Compose seats on flight
        FlightSeats fsEconomy = new FlightSeats(SEAT_COUNT_ECONOMY, SEAT_PRICE_ECONOMY, flight, SeatType.ECONOMY);
        FlightSeats fsBusiness = new FlightSeats(SEAT_COUNT_BUSINESS, SEAT_PRICE_BUSINESS, flight, SeatType.BUSINESS);
        ArrayList<FlightSeats> fs = new ArrayList<>();
        fs.add(fsEconomy);
        fs.add(fsBusiness);
        flight.setFlightSeats(fs);

        //Compose journeys taken by flight

        //Ensure the departure and arrival datetimes are in sequence...
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(new Date());

        ArrayList<Itinerary> journeys = new ArrayList<>();
        for (int i = 0; i < iatas.length - 1; i++) {
            FlightSegment seg = getSegmentByIATA(iatas[i], iatas[i + 1]);
            Itinerary journey = new Itinerary(seg, flight);
            //Set datetimes
            Calendar dep = (Calendar) startDate.clone();
            dep.add(Calendar.DATE, RandomGenerator.getRandomInt(1, 15));

            //Arrival dependant on departure
            Calendar arr = (Calendar) dep.clone();
            arr.add(Calendar.HOUR, RandomGenerator.getRandomInt(2, 48));
            startDate = (Calendar) arr.clone();

            journey.setDepartureDateTime(dep.getTime());
            journey.setArrivalDateTime(arr.getTime());

            journeys.add(journey);
        }
        flight.setJourney(journeys);
        return flight;
    }

    //TODO move to flight controller
    private FlightSegment getSegmentByIATA(String iataDep, String iataArr) {
        for (FlightSegment seg : this.flightSegments) {
            if (seg.getDepartureAirport().getIataCode().equals(iataDep) &&
                    seg.getArrivalAirport().getIataCode().equals(iataArr)) {
                return seg;
            }
        }
        return null;
    }

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
}
