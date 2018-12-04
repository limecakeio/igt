package de.hsma.igt.flightsystem.tools;

import de.hsma.igt.flightsystem.models.Airport;

import java.util.*;

public class AirportPopulator {
    private ArrayList<Airport> airports;

    public AirportPopulator() {
        this.initAirports();
    }

    private void initAirports() {
        this.airports.add(new Airport("SIN", "Singapore Changi Airport", 1.35019, 103.994003));
        this.airports.add(new Airport("SYD", "Sydney Kingsford Smith International Airport", -33.94609832763672, 151.177001953125));
        this.airports.add(new Airport("HEL", "Helsinki Vantaa Airport", 60.317199707031, 24.963300704956));
        this.airports.add(new Airport("GOT", "Gothenburg-Landvetter Airport", 57.662799835205, 12.279800415039));
        this.airports.add(new Airport("BGO", "Bergen Airport Flesland", 60.29339981, 5.218140125));
        this.airports.add(new Airport("DUT", "Unalaska Airport", 53.900100708, -166.544006348));
        this.airports.add(new Airport("MAN", "Manchester Airport", 53.35369873046875, -2.2749500274658203));
        this.airports.add(new Airport("BRE", "Bremen Airport", 53.0475006104, 8.78666973114));
        this.airports.add(new Airport("KRK", "Kraków John Paul II International Airport", 50.077702, 19.7848));
        this.airports.add(new Airport("KOO", "Kongolo Airport", -5.39444, 26.99));
    }

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public void addAirport(Airport a) {
        this.airports.add(a);
    }
}
