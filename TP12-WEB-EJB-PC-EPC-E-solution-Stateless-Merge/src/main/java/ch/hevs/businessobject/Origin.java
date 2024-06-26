package ch.hevs.businessobject;

import java.util.HashSet;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Origin extends Country{
    
    private String terminal;


    //a Origin can have multiple flights
    @OneToMany(mappedBy = "origin")
    private Set<Flight> flights = new HashSet<>();


    public Origin(String City, String Name, String airportCode, String terminal) {
        super(City, Name, airportCode);
        this.terminal = terminal;
    }
    
    //getters, and setters
    public String getTerminal() { return terminal; }
    public void setTerminal(String terminal) { this.terminal = terminal; }

    public Set<Flight> getFlights() { return flights; }
    public void setFlights(Set<Flight> flights) { this.flights = flights; }
   
}
