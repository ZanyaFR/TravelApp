package ch.hevs.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.reservationservice.Reservation;
import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Origin;
import ch.hevs.businessobject.Passenger;
import ch.hevs.businessobject.Destination;

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
    private Reservation reservation;

    private List<Passenger> passengers;
    private List<String> passengerNames;
    private String selectedPassenger; // For selecting a client
    
    private List<Flight> flights;
    private Flight selectedFlight; // For selecting a flight
    
    private List<Destination> flightsDestination;
    private List<String> destinationNames;
    private String selectedDestination; // For selecting a departure location


    private List<Origin> flightsOrigin;
    private List<String> originNames;
    private String selectedOrigin; // For selecting an arrival location

    @PostConstruct
    public void initialize() {
        
        try {
        // use JNDI to inject reference to Reservation EJB
        InitialContext ctx = new InitialContext();
        reservation = (Reservation) ctx.lookup("java:global/TP12-WEB-EJB-PC-EPC-E-1.0.0/ReservationBean!ch.hevs.reservationservice.Reservation");

        // get Flights & their destinations and Origin
        List<Flight> flightsList = reservation.getFlights();
        this.flightsDestination = new ArrayList<Destination>();
        for (Flight flight : flightsList) {
            this.flightsDestination.add(flight.getDestination());
        }
        for (Destination destination : flightsDestination) {
            this.destinationNames.add(destination.getCity());
        }
        this.flightsOrigin = new ArrayList<Origin>();
        for (Flight flight : flightsList) {
            this.flightsOrigin.add(flight.getOrigin());
        }
        for (Origin origin : flightsOrigin) {
            this.originNames.add(origin.getCity());
        }

        // get passengers
        List<Passenger> passengerList = reservation.getPassengers();
        this.passengers = new ArrayList<Passenger>();
        for (Passenger passenger : passengerList) {
            this.passengers.add(passenger);
        }
        for (Passenger passenger : passengers) {
            this.passengerNames.add(passenger.getFirstName() + " " + passenger.getLastName());
        }
        } catch (NamingException e) {
            e.printStackTrace();
            System.err.println("Error initializing ReservationManagedBean: " + e.getMessage());

        }

    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void updateFlights(ValueChangeEvent event) {
        String passengerName = (String) event.getNewValue();
        flights = reservation.getFlightsFromPassengerName(passengerName);
    }

    // Getters and setters for the new properties
    public String getSelectedPassenger() {
        return selectedPassenger;
    }
    public void setSelectedPassenger(String selectedPassenger) {
        this.selectedPassenger = selectedPassenger;
    }

    public Flight getSelectedFlight() {
        return selectedFlight;
    }
    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public List<Destination> getFlightsDestination() {
        return flightsDestination;
    }

    public void setFlightsDestination(List<Destination> flightsDestination) {
        this.flightsDestination = flightsDestination;
    }

    public String getSelectedDestination() {
        return selectedDestination;
    }

    public void setSelectedDestination(String selectedDestination) {
        this.selectedDestination = selectedDestination;
    }

    public List<Origin> getFlightsOrigin() {
        return flightsOrigin;
    }

    public void setFlightsOrigin(List<Origin> flightsOrigin) {
        this.flightsOrigin = flightsOrigin;
    }

    public String getSelectedOrigin() {
        return selectedOrigin;
    }

    public void searchFlights() {
        // Get the list of flights that destination and Origin match the input from reservationForm.xhtml


        }
    
    // Method to perform booking
    public void performBooking() {
        // Add my booking data to the database        


        
    }

}