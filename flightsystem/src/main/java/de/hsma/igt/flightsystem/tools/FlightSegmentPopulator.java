package de.hsma.igt.flightsystem.tools;

import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.FlightSegment;

import java.util.ArrayList;

public class FlightSegmentPopulator {
    private ArrayList<Airport> airports;
    private ArrayList<FlightSegment> flightSegments;

    public FlightSegmentPopulator(ArrayList<Airport> airports) {
        this.airports = airports;
        this.initFlightSegments();
    }

    private void initFlightSegments() {
        for (Airport departure : this.airports) {
            for (Airport arrival : this.airports) {
                if (departure != arrival) {
                    int distance = getDistanceInMiles(departure.getLatitude(), departure.getLongitude(), arrival.getLatitude(), arrival.getLongitude());
                    FlightSegment segment = new FlightSegment(departure.getIataCode() + " -> " + arrival.getIataCode(), distance);
                    this.flightSegments.add(segment);
                }
            }
        }
    }

    public static void main(String[] args) {
        AirportPopulator ap = new AirportPopulator();
        ArrayList<Airport> airs = ap.getAirports();
        FlightSegmentPopulator fsp = new FlightSegmentPopulator(airs);
        System.out.println("");
    }

    public static int getDistanceInMiles(double latA, double longA, double latB, double longB) {
        latA = Math.toRadians(latA);
        latB = Math.toRadians(latB);
        double eRadius = 6371000;
        double phi = Math.toRadians(latA - latB);
        double lambda = Math.toRadians(longB - longA);
        double a = (Math.sin(phi/2) * Math.sin(phi/2)) + (Math.cos(latA) * Math.cos(latB)) * (Math.sin(lambda/2) * Math.sin(lambda/2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return (int)(eRadius * c * 0.621); //0.621 is miles per kilometer
    }
}


