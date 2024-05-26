package ch.hevs.businessobject;

import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Destination extends Country{

    private String BaggageClaim;

    //a Destination can have multiple flights
     @OneToMany(mappedBy = "destination")
    private Set<Flight> flights = new HashSet<>();


    public Destination( String City, String Name,
     String airportCode, String BaggageClaim) {
        super( City, Name, airportCode);
        this.BaggageClaim = BaggageClaim;
    }

    //getters, and setters

    //Baggage Claim
    public String getBaggageClaim() { return BaggageClaim; }
    public void setBaggageClaim(String BaggageClaim) { this.BaggageClaim = BaggageClaim; }

    
    //Flights
    public Set<Flight> getFlights() { return flights; }
    public void setFlights(Set<Flight> flights) { this.flights = flights; }
   
}
