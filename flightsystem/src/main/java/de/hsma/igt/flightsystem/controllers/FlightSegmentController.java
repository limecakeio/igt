package de.hsma.igt.flightsystem.controllers;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.apache.log4j.Logger;

import de.hsma.igt.flightsystem.models.FlightSegment;
import de.hsma.igt.flightsystem.models.FlightSegment;
import de.hsma.igt.flightsystem.tools.Config;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class FlightSegmentController implements IController<FlightSegment> {

	private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = null;
	
	public FlightSegmentController(PersistenceUnit persistenceUnit) {
		emf = Persistence.createEntityManagerFactory(persistenceUnit.name());
	}
	
    
	@Override
	public void createObjects(List<FlightSegment> objects) {
		try {
			EntityManager em =emf.createEntityManager();
			//tm.setTransactionTimeout(seconds);
			tm.begin();
			for(FlightSegment flightSegment: objects){
				em.persist(flightSegment.getArrivalAirport());
				em.persist(flightSegment.getDepartureAirport());
				em.persist(flightSegment);
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
        } catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateObjects(List<FlightSegment> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObjects(List<FlightSegment> objects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FlightSegment> readObjects() {
		List<FlightSegment> flightSegments = new ArrayList<FlightSegment>();

		try {
			EntityManager em = emf.createEntityManager();

			String queryString = new String("SELECT f FROM FlightSegment f");

			logger.info("Get all customer TA begins");
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			Query q = em.createQuery(queryString);

			long queryStart = System.currentTimeMillis();

			flightSegments = q.getResultList();

			long queryEnd = System.currentTimeMillis();

			em.flush();
			em.close();
			tm.commit();

			logger.info("Get all customer TA ends");

			long queryTime = queryEnd - queryStart;

			logger.info("Found " + flightSegments.size() + " Flightsegments in " + queryTime + " ms.");

			String writeToFile = new String(
					Config.PERSISTENCE_UNIT_NAME + " READ  : " + flightSegments.size() + " " + queryTime + "\n");
			Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);

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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flightSegments;
	}

}
