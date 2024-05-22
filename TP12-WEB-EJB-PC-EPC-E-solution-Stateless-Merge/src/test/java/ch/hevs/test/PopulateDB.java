package ch.hevs.test;

import java.sql.SQLException;

import org.junit.Test;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Passenger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import junit.framework.TestCase;


public class PopulateDB extends TestCase {

	@Test
	public void test() throws SQLException, ClassNotFoundException {
		
		EntityTransaction tx = null;
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU_unitTest");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
		
			Client c1 = new Client("Zinedine", "Zidane");
			Account a1 = new Account("1000", 10000, c1, "Compte Courant");
			// ...
			//    public Passenger(String firstName, String lastName, String email, String phoneNumber) {
			Passenger p1 = new Passenger("John", "Doe", "ss","1234567890");

			em.persist(a1);
			em.persist(c1);
			em.persist(p1);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
