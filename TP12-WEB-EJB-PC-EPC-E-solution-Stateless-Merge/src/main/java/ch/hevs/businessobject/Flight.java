package ch.hevs.businessobject;

import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String departureDate;
    private String departureTime;
    private String arrivalDate;
    private String arrivalTime;
    private double price;
    private boolean isFull;

     @OneToMany(mappedBy = "flight")
    private Set<Passenger> passengers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;


    //constructors

    Flight() {
    }

    public Flight(String departureDate, String departureTime, String arrivalDate, 
    String arrivalTime,Origin origin, Destination destination, int price, boolean isFull) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.isFull = isFull;
        }
    
    //getters, and setters
    
    public String getDepartureDate() {   return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate;}
    
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}
    
    public String getArrivalDate() { return arrivalDate;}
    public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }
    
    public String getArrivalTime() { return arrivalTime;}
    public void setArrivalTime(String arrivalTime) {this.arrivalTime = arrivalTime;}
    
    // public String getOrigin() { return origin;}
    // public void setOrigin(String origin) {this.origin = origin;  }
    
    // public String getDestination() { return destination; }
    // public void setDestination(String destination) {this.destination = destination;}
    
    public double getPrice() { return price; }
    public void setPrice(double price) {this.price = price;}
    
    public boolean isFull() { return isFull;}
    public void setFull(boolean isFull) {this.isFull = isFull;}
    
}
