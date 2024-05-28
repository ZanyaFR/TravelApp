package ch.hevs.reservationservice;

import java.util.List;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Passenger;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.Query;

@Stateless
public class ReservationBean implements Reservation {
 
    @PersistenceContext(unitName = "reservationPU", type=PersistenceContextType.TRANSACTION)
    private EntityManager em;

    // Flight-related methods
    public Flight getFlight(String flightNumber) {
        Query query = em.createQuery("FROM Flight f WHERE f.number=:number");
        query.setParameter("number", flightNumber);
        Flight flight = (Flight) query.getSingleResult();
        return flight;
    }

    public Flight getFlightById(long flightId) {
        return (Flight) em.createQuery("FROM Flight f where f.id=:id").setParameter("id", flightId).getSingleResult();
    }

    public List<Flight> getFlights() {
        return em.createQuery("FROM Flight").getResultList();
    }

    public List<Flight> getFlightsFromPassengerName(String passengerName) {
        return (List<Flight>) em.createQuery("SELECT p.flights FROM Passenger p where p.lastname=:lastname").setParameter("lastname", passengerName).getResultList();
    }

    // Passenger-related methods
    public Passenger getPassenger(long passengerId) {
        return (Passenger) em.createQuery("FROM Passenger p where p.id=:id").setParameter("id", passengerId).getSingleResult();
    }

    public List<Passenger> getPassengers() {
        return em.createQuery("FROM Passenger").getResultList();
    }

    public void addPassenger(Passenger passenger) {
        em.persist(passenger);
    }

    // Booking-related method
    public Flight bookFlight(Passenger passenger, Flight flight) {
       
       EntityTransaction tx = null;
		try {
        tx = em.getTransaction();
        tx.begin();

        Passenger realPassenger = em.merge(passenger);
        Flight realFlight = em.merge(flight);
        realPassenger.setFlight(realFlight);
        realFlight.setPassenger(realPassenger);

        realFlight.setNbPassengers(realFlight.getNbPassengers() + 1);

        em.persist(realFlight);
        em.persist(realPassenger);

        tx.commit();

        return realFlight;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            return null;
        }


    }

    //update flight
    public void updateFlight(Flight flight) {
        em.merge(flight);
    }

    //get flights from origin and destination
    public List<Flight> getFlightsFromOriginDestination(String selectedOrigin, String selectedDestination) {
        Query query = em.createQuery("SELECT f FROM Flight f WHERE f.origin.city=:origin AND f.destination.city=:destination");
        query.setParameter("origin", selectedOrigin);
        query.setParameter("destination", selectedDestination);
        return query.getResultList();
    }

}