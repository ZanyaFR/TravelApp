package ch.hevs.businessobject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class Country{
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String City;
    private String Name;
    private String airportCode;


    public Country() {}
    
    public Country(String City, String Name, String airportCode) {
        this.City = City;
        this.Name = Name;
        this.airportCode = airportCode;
    }


    //ID
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    //City
    public String getCity() { return City; }
    public void setCity(String city) { City = city; }

    //Name
    public String getName() { return Name; }
    public void setName(String name) { Name = name; }

    //Airport Code
    public String getAirportCode() { return airportCode; }
    public void setAirportCode(String airportCode) { this.airportCode = airportCode; }
}