package ch.hevs.managedbeans;

import java.io.Serializable;
import java.util.List;

import ch.hevs.businessobject.Passenger;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * PassengerBean :
 * This bean handles all transactions related to our passengers.
 */

@ManagedBean
@SessionScoped
@Named("passengerBean")
public class PassengerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // Inject the EntityManager to handle database operations
    @PersistenceContext
    private EntityManager em;

    private List<Passenger> passengers;
    private Passenger selectedPassenger;

     // ensures this method runs after the bean's initialization
     @PostConstruct
     public void initialize() {
         loadPassengers();
     }
 
     // load all passengers from the database
    private void loadPassengers() {
        TypedQuery<Passenger> query = em.createQuery("SELECT p FROM Passenger p", Passenger.class);
        passengers = query.getResultList();
    }

     // Method to create a new passenger
     public void createPassenger(String firstName, String lastName, String email, String phoneNumber) {
        Passenger newPassenger = new Passenger();
        newPassenger.setFirstName(firstName);
        newPassenger.setLastName(lastName);
        newPassenger.setEmail(email);
        newPassenger.setPhoneNumber(phoneNumber);
        
        em.persist(newPassenger);
        em.flush();
        
        loadPassengers(); // Reload passengers to include the new one
    }

    // Method to update the selected passenger
    public void updatePassenger() {
        if (selectedPassenger != null) {
            em.merge(selectedPassenger);
            em.flush();
            loadPassengers(); // Reload passengers to reflect the update
        }
    }

    // Method to delete the selected passenger
    public void deletePassenger() {
        if (selectedPassenger != null) {
            em.remove(em.contains(selectedPassenger) ? selectedPassenger : em.merge(selectedPassenger));
            em.flush();
            loadPassengers(); // Reload passengers to reflect the deletion
        }
    }

    // Getters and setters
    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Passenger getSelectedPassenger() {
        return selectedPassenger;
    }

    public void setSelectedPassenger(Passenger selectedPassenger) {
        this.selectedPassenger = selectedPassenger;
    }

}

