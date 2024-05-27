package ch.hevs.reservationservice;

import java.util.List;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Passenger;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;

@Stateless
public class ReservationBean implements Reservation {
 
    @PersistenceContext(unitName = "reservationPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public Flight getFlight(String flightNumber) {
        
        Query query = em.createQuery("FROM Flight f WHERE f.number=:number");
        query.setParameter("number", flightNumber);
        
        Flight flight = (Flight) query.getSingleResult();
        
        return flight;
    }
    
    public List<Flight> getFlightsFromPassengerName(String passengerName) {
        return (List<Flight>) em.createQuery("SELECT p.flights FROM Passenger p where p.lastname=:lastname").setParameter("lastname", passengerName).getResultList();
    }

    //make the reservation method :
    public void bookFlight(Passenger passenger, Flight flight) {
        
        
        Passenger realPassenger = em.merge(passenger);
        Flight realFlight = em.merge(flight);
        
        realPassenger.setFlight(realFlight);
        realFlight.setPassenger(realPassenger);
    }

    public List<Passenger> getPassengers() {
        return em.createQuery("FROM Passenger").getResultList();
    }
    
    public Passenger getPassenger(long passengerId) {
        return (Passenger) em.createQuery("FROM Passenger p where p.id=:id").setParameter("id", passengerId).getSingleResult();
    }

    
}