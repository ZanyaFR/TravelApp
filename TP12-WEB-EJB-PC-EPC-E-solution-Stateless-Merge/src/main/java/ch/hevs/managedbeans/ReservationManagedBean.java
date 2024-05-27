package ch.hevs.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.reservationservice.Reservation;
import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Passenger;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ValueChangeEvent;
import jakarta.inject.Named;

@ManagedBean
@SessionScoped
@Named("reservationBean")
public class ReservationManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Flight> flights;
    private List<Passenger> passengers;
    private Reservation reservationService;

    @PostConstruct
    public void initialize() throws NamingException {
        InitialContext ctx = new InitialContext();
        reservationService = (Reservation) ctx.lookup("java:global/ReservationService/ReservationBean!ch.hevs.reservationservice.Reservation");

        passengers = reservationService.getPassengers();
        flights = reservationService.getFlightsFromPassengerName(passengers.get(0).getLastName());
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void updateFlights(ValueChangeEvent event) {
        String passengerName = (String) event.getNewValue();
        flights = reservationService.getFlightsFromPassengerName(passengerName);
    }
}