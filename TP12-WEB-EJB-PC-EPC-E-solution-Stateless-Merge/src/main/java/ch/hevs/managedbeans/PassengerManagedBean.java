package ch.hevs.managedbeans;

import java.io.Serializable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Passenger;
import ch.hevs.reservationservice.Reservation;

@Named("passengerManagedBean")
@SessionScoped
public class PassengerManagedBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Reservation reservation;
    private Passenger newPassenger = new Passenger();

    @PostConstruct
    public void initialize() {
        try {
            InitialContext ctx = new InitialContext();
            reservation = (Reservation) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-1.0.0/ReservationBean!ch.hevs.reservationservice.Reservation");
        
        } catch (NamingException e) {
            e.printStackTrace();
            System.err.println("Error initializing PassengerManagedBean: " + e.getMessage());
        }
    }

    public Passenger getNewPassenger() {
        return newPassenger;
    }

    public void setNewPassenger(Passenger newPassenger) {
        this.newPassenger = newPassenger;
    }

    public void addPassenger() {
        try {
            reservation.addPassenger(newPassenger);
            newPassenger = new Passenger();  // Reset the form after adding the passenger
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error adding passenger: " + e.getMessage());
        }
    }
}

    

