package ch.hevs.reservationservice;

import java.util.List;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Passenger;

import jakarta.ejb.Local;

/*interface for a local Enterprise JavaBean (EJB). 
It defines several methods that can be used to interact
with our FLights and Passengers
*/

@Local
public interface Reservation {

    
    Flight getFlight(String flightNumber);

    public List<Flight> getFlightsFromPassengerName(String passengerName);

    void bookFlight(Passenger passenger, Flight flight) throws Exception;

    List<Passenger> getPassengers();

    Passenger getPassenger(long passengerId);

    
}
