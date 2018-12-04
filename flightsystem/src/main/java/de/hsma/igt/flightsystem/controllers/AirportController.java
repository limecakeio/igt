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

import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.CustomerPhone;
import de.hsma.igt.flightsystem.tools.Config;

public class AirportController implements IController<Airport>{
	private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);
    
	@Override
	public void createObjects(List<Airport> objects) {
		try {
			EntityManager em =emf.createEntityManager();
			//tm.setTransactionTimeout(seconds);
			tm.begin();
			for(Airport airport: objects){
				System.out.println(airport.getName());
				em.persist(airport);
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
	public void updateObjects(List<Airport> objects) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteObjects(List<Airport> objects) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Airport> readObjects() {
		List<Airport> airports = new ArrayList<Airport>();

		try {
			EntityManager em = emf.createEntityManager();

			String queryString = new String("SELECT a FROM Airport a");

			logger.info("Get all customer TA begins");
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			Query q = em.createQuery(queryString);

			long queryStart = System.currentTimeMillis();

			airports = q.getResultList();

			long queryEnd = System.currentTimeMillis();

			em.flush();
			em.close();
			tm.commit();

			logger.info("Get all customer TA ends");

			long queryTime = queryEnd - queryStart;

			logger.info("Found " + airports.size() + " airports in " + queryTime + " ms.");

			String writeToFile = new String(
					Config.PERSISTENCE_UNIT_NAME + " READ  : " + airports.size() + " " + queryTime + "\n");
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
		return airports;
	}
}
