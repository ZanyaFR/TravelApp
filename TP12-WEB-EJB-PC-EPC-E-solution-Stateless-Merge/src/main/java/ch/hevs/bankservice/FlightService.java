package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Flight;
import jakarta.ejb.Local;

@Local
public interface FlightService {

    Flight getFlight(String flightNumber);

    List<Flight> getAllFlights();

    void addFlight(Flight flight);
}

