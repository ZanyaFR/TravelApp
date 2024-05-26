package ch.hevs.businessobject;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Passenger {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;


    public Passenger(String firstName, String lastName, String email, String phoneNumber, Flight flight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.flight = flight;
    }


    public Passenger() {}

    //ID
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    //First Name
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    //Last Name
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    //Email
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    //Phone Number
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}

    //Flight
    public Flight getFlight() {return flight;}
    public void setFlight(Flight flight) {this.flight = flight;}
}
