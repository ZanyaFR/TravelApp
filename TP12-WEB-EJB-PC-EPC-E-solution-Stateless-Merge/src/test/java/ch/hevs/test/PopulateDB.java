package ch.hevs.test;

import java.sql.SQLException;

import org.junit.Test;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;

import ch.hevs.businessobject.Country;
import ch.hevs.businessobject.Destination;
import ch.hevs.businessobject.Flight;
import ch.hevs.businessobject.Origin;
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
			
			
			
			Destination d1 = new Destination("Geneva", "Switzerland", "GVA", "5");
			Origin o1 = new Origin("London", "UK", "LHR", "3");
			Passenger p1 = new Passenger("John", "Doe", "ss","1234567890");
			Flight f1 = new Flight("2021-12-12", "12:00", "2021-12-12", "14:00", "London", "Geneva", 100, false);

			em.persist(o1);
			em.persist(d1);
			em.persist(p1);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
