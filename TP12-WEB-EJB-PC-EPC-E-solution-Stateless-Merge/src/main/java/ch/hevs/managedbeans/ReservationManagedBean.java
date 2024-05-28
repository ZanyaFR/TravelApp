package ch.hevs.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
@Named("reservationManagedBean")
public class ReservationManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Reservation reservation;

    private List<Passenger> passengers;
    private List<String> passengerNames;
    private Passenger selectedPassenger; // For selecting a client
    
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

            // Initialize flightsDestination and destinationNames
            this.flightsDestination = new ArrayList<>();
            this.destinationNames = new ArrayList<>();
            for (Flight flight : flightsList) {
                this.flightsDestination.add(flight.getDestination());
            }
            for (Destination destination : flightsDestination) {
                this.destinationNames.add(destination.getCity());
            }

            // Initialize flightsOrigin and originNames
            this.flightsOrigin = new ArrayList<>();
            this.originNames = new ArrayList<>();
            for (Flight flight : flightsList) {
                this.flightsOrigin.add(flight.getOrigin());
            }
            for (Origin origin : flightsOrigin) {
                this.originNames.add(origin.getCity());
            }

            // get passengers
            List<Passenger> passengerList = reservation.getPassengers();
            this.passengers = new ArrayList<>();
            this.passengerNames = new ArrayList<>();
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
    public Passenger getSelectedPassenger() {
        return selectedPassenger;
    }
    public void setSelectedPassenger(Passenger selectedPassenger) {
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

    public void setSelectedOrigin(String selectedOrigin) {
        this.selectedOrigin = selectedOrigin;
    }

    public List<String> getOriginNames() {
        return originNames;
    }

    public void setOriginNames(List<String> originNames) {
        this.originNames = originNames;
    }

    public List<String> getDestinationNames() {
        return destinationNames;
    }

    public void setDestinationNames(List<String> destinationNames) {
        this.destinationNames = destinationNames;
    }

    public List<String> getPassengerNames() {
        return passengerNames;
    }

    public void setPassengerNames(List<String> passengerNames) {
        this.passengerNames = passengerNames;
    }

    // Method to perform booking
    public void performBooking() {
        // Add my booking data to the database
    }
}
