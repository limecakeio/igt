package de.hsma.igt.flightsystem.controllers;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.apache.log4j.Logger;

import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.models.FlightSeats;
import de.hsma.igt.flightsystem.tools.Config;

public class FlightController implements IController<Flight>{

	private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
    
	@Override
	public void createObjects(List<Flight> objects) {
		try {
			EntityManager em =emf.createEntityManager();
			//tm.setTransactionTimeout(seconds);
			tm.begin();
			for(Flight flight: objects){
				em.persist(flight);
				for(FlightSeats seats : flight.getFlightSeats())
					em.persist(seats);
			}
			em.flush();
			em.close();
			tm.commit();
    	} catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void updateObjects(List<Flight> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObjects(List<Flight> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Flight> readObjects() {
		// TODO Auto-generated method stub
		return null;
	}

}