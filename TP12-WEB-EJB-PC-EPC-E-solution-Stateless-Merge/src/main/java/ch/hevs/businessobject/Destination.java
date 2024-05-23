package ch.hevs.businessobject;

import jakarta.persistence.Entity;

@Entity
public class Destination extends Country{

    private String BaggageClaim;


    public Destination( String City, String Name,
     String airportCode, String BaggageClaim) {
        super( City, Name, airportCode);
        this.BaggageClaim = BaggageClaim;
    }

    //getters, and setters
    public String getBaggageClaim() { return BaggageClaim; }
    public void setBaggageClaim(String BaggageClaim) { this.BaggageClaim = BaggageClaim; }
   
}
