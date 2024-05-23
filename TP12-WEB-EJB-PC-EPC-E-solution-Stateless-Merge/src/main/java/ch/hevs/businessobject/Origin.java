package ch.hevs.businessobject;

import jakarta.persistence.Entity;

@Entity
public class Origin extends Country{
    
    private String terminal;


    public Origin(String City, String Name, String airportCode, String terminal) {
        super(City, Name, airportCode);
        this.terminal = terminal;
    }
    
    //getters, and setters
    public String getTerminal() { return terminal; }
    public void setTerminal(String terminal) { this.terminal = terminal; }
   
}
