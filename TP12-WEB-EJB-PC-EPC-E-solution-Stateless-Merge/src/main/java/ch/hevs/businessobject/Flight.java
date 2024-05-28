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
    private int capacity;
    private int nbPassengers;
    private boolean isFull;

     @OneToMany(mappedBy = "flight")
     //set not List to avoid repeats
    private Set<Passenger> passengers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;


    //constructors

    Flight() {    }

    public Flight(String departureDate, String departureTime, String arrivalDate, 
    String arrivalTime,Origin origin, Destination destination, int price,int capacity, boolean isFull) {
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
        this.capacity = capacity;
        this.nbPassengers = 0;
        this.isFull = isFull;
        }
    
    //getters, and setters
    
    //ID
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    //Departure date
    public String getDepartureDate() {   return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate;}
    
    //Departure time
    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}
    
    //Arrival date
    public String getArrivalDate() { return arrivalDate;}
    public void setArrivalDate(String arrivalDate) { this.arrivalDate = arrivalDate; }
    
    //Arrival time
    public String getArrivalTime() { return arrivalTime;}
    public void setArrivalTime(String arrivalTime) {this.arrivalTime = arrivalTime;}
    
    //Price
    public double getPrice() { return price; }
    public void setPrice(double price) {this.price = price;}

    //Capacity
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) {this.capacity = capacity;}

    //nbPassengers
    public int getNbPassengers() { return nbPassengers; }
    public void setNbPassengers(int nbPassengers) {
        if (nbPassengers > capacity){
            throw new IllegalStateException("Flight is full, Pick another one.");
        }else{
        this.nbPassengers =+ nbPassengers;
        }
    }
    
    //isFull
    public boolean isFull() { return isFull;}
    public void setFull(boolean isFull) {this.isFull = isFull;}
   
    //Passengers list
    public Set<Passenger> getPassengers(){return passengers;}
    public void setPassengers(Set<Passenger> passengers) { 

        if (passengers.size() == capacity) {
            isFull = true;
        }else if(passengers.size() > capacity || isFull == true){
                throw new IllegalStateException("Flight is full, Pick another one.");}
        else{
        this.passengers = passengers;
        setNbPassengers(passengers.size());
        }

    }

    //Passenger
    public void setPassenger(Passenger Passenger){
        if (isFull){
            throw new IllegalStateException("Flight is full, Pick another one.");
        }else{
        passengers.add(Passenger);
        setNbPassengers(1);
        }
    }

    //Destination
    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }
    
    //Origin
    public Origin getOrigin() { return origin; }
    public void setOrigin(Origin origin) { this.origin = origin; }

}
