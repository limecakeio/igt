package de.hsma.igt.flightsystem.controllers;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.models.Itinerary;
import de.hsma.igt.flightsystem.tools.Config;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class FlightController extends GenericController<Flight>{

	public FlightController(PersistenceUnit persistenceUnit) {
		super(persistenceUnit, Flight.class);
	}

	public List<Flight> readByAirport(Airport airport) {
		List<Flight> objectList = new ArrayList<Flight>();
		try {
			EntityManager em = emf.createEntityManager();

			String queryString = new String(
					"SELECT distinct x.flight FROM " + Itinerary.class.getSimpleName() + " x WHERE x.flightSegment.arrivalAirport = "
							+ airport.getId() + " OR x.flightSegment.departureAirport = " + airport.getId());

			logger.info("Get "+Flight.class.getSimpleName()+"from and to "+airport.getName()+" TA begins");
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			Query q = em.createQuery(queryString);

			long queryStart = System.currentTimeMillis();

			objectList = q.getResultList();

			long queryEnd = System.currentTimeMillis();

			em.flush();
			em.close();
			tm.commit();

			logger.info("Get all "+Flight.class.getSimpleName()+" TA ends");

			long queryTime = queryEnd - queryStart;

			logger.info("Found " + objectList.size() + " " +Flight.class.getSimpleName()+" in " + queryTime + " ms.");

			String writeToFile = new String(this.persistenceUnit.name() + " READ  : " + objectList.size() + " " + queryTime + "\n");
			Files.write(Paths.get(Config.LOG + this.persistenceUnit.name() + Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);

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
		
		return objectList;
	}
}
