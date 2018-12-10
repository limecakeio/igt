package de.hsma.igt.flightsystem.controllers;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import de.hsma.igt.flightsystem.models.Booking;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.tools.Config;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class NestedController extends GenericController<Customer> {

	public NestedController(PersistenceUnit persistenceUnit) {
		super(persistenceUnit, Customer.class);
	}
	
	public void nestedTransaction(List<Customer> objects) throws NotSupportedException {
		try {
			logger.info("Create " + Customer.class.getSimpleName() + " TA begins");
			EntityManager em = emf.createEntityManager();
			long queryStart = System.currentTimeMillis();
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			for (Customer objecet : objects) {
				tm.begin();
				em.persist(objecet);
				tm.commit();
			}
			long queryEnd = System.currentTimeMillis();
			em.flush();
			em.close();
			tm.commit();

			logger.info("Create " + Customer.class.getSimpleName() + " TA ends");
			long queryTime = queryEnd - queryStart;
			logger.info(objects.size() + " " + Customer.class.getSimpleName() + " persisted in DB in " + queryTime
					+ " ms.");
			String writeToFile = new String(
					this.persistenceUnit.name() + " CREATE: " + objects.size() + " " + queryTime + "\n");
			
			File f = new File(Config.PERSIST_STORAGE_LOCATION);
			f.getParentFile().mkdirs();
			Files.createDirectories(Paths.get(Config.PERSIST_STORAGE_LOCATION).getParent());
			Files.write(Paths.get(Config.PERSIST_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
		
		
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
	}

}
