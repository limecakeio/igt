package de.hsma.igt.flightsystem.tools;

import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.FlightSegment;

import java.util.ArrayList;

public class FlightSegmentPopulator {
    private ArrayList<Airport> airports;
    private ArrayList<FlightSegment> flightSegments;

    public FlightSegmentPopulator(ArrayList<Airport> airports) {
        this.airports = airports;
        this.flightSegments = new ArrayList<>();
        this.initFlightSegments();
    }

    private void initFlightSegments() {
        for (Airport departure : this.airports) {
            for (Airport arrival : this.airports) {
                if (departure != arrival) {
                    FlightSegment segment = new FlightSegment(departure, arrival);
                    this.flightSegments.add(segment);
                }
            }
        }
    }
}


