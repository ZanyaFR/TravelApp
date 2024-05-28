package ch.hevs.reservationservice;

import java.util.List;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Passenger;

import jakarta.ejb.Local;

/*interface for a local Enterprise JavaBean (EJB). 
It defines several methods that can be used to interact
with our Flights and Passengers
*/

@Local
public interface Reservation {

    // Flight-related methods
    Flight getFlight(String flightNumber);
    Flight getFlightById(long flight);
    List<Flight> getFlights();
    List<Flight> getFlightsFromPassengerName(String passengerName);
    List<Flight> getFlightsFromOriginDestination(String selectedOrigin, String selectedDestination);

    // Passenger-related methods
    Passenger getPassenger(long passengerId);
    List<Passenger> getPassengers();
    void addPassenger(Passenger passenger);

    // Booking-related method
    Flight bookFlight(Passenger passenger, Flight flight) throws Exception;
}