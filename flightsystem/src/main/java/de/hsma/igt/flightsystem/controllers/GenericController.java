package de.hsma.igt.flightsystem.controllers;

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
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.File;
import java.io.IOException;

import de.hsma.igt.flightsystem.models.BaseEntity;
import de.hsma.igt.flightsystem.tools.Config;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public abstract class GenericController<T extends BaseEntity> implements IController<T> {

	private Class<T> persistentClass;
	protected static Logger logger = Logger.getRootLogger();
	protected static TransactionManager tm = null;
	protected static EntityManagerFactory emf = null;
	protected PersistenceUnit persistenceUnit;

	@SuppressWarnings("unchecked")
	public GenericController(PersistenceUnit persistenceUnit, Class<T> persistentClass) {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory(persistenceUnit.name());	
		}
		
		if(tm == null) {
			tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
		}
		
		if(persistenceUnit == null) {
			
		}
		this.persistentClass = persistentClass;
		this.persistenceUnit = persistenceUnit;
	}

	@Override
	public void createObjects(List<T> objects) {
		try {
			logger.info("Create " + this.persistentClass.getSimpleName() + " TA begins");
			EntityManager em = emf.createEntityManager();
			long queryStart = System.currentTimeMillis();
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			for (T objecet : objects) {
				em.persist(objecet);
			}
			long queryEnd = System.currentTimeMillis();
			em.flush();
			em.close();
			tm.commit();

			logger.info("Create " + this.persistentClass.getSimpleName() + " TA ends");
			long queryTime = queryEnd - queryStart;
			logger.info(objects.size() + " " + this.persistentClass.getSimpleName() + " persisted in DB in " + queryTime
					+ " ms.");
			String writeToFile = new String(
					this.persistenceUnit.name() + " CREATE: " + objects.size() + " " + queryTime + "\n");
			
			File f = new File(Config.PERSIST_STORAGE_LOCATION);
			f.getParentFile().mkdirs();
			Files.createDirectories(Paths.get(Config.PERSIST_STORAGE_LOCATION).getParent());
			Files.write(Paths.get(Config.PERSIST_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
			
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
	}

	@Override
	public void updateObjects(List<T> objects) {
		try {

			logger.info("Update " + this.persistentClass.getSimpleName() + " TA begins");
			EntityManager em = emf.createEntityManager();
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			long queryStart = System.currentTimeMillis();
			for (T object : objects) {
				T objectToUpdate = (T) em.find(object.getClass(), object.getId());
				objectToUpdate = object;
				em.merge(objectToUpdate);
			}
			long queryEnd = System.currentTimeMillis();

			em.flush();
			em.close();
			tm.commit();
			logger.info("Update "+this.persistentClass.getSimpleName()+" TA ends");

			long queryTime = queryEnd - queryStart;
			logger.info("Updates of " + objects.size() + persistentClass.getSimpleName()+" successfully persisted in " + queryTime + " ms.");
			String writeToFile = new String(this.persistenceUnit.name() + " UPDATE: " + objects.size() + " " + queryTime + "\n");
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
	}

	@Override
	public void deleteObjects(List<T> objects) {
		try {
			
			logger.info("Delete all "+persistentClass.getSimpleName()+" TA begins");
			EntityManager em = emf.createEntityManager();
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();
			long queryStart = System.currentTimeMillis();
			
			for (T object : objects) {
				T objectToDelete = (T) em.find(object.getClass(), object.getId());
				em.remove(objectToDelete);
			}
			
			long queryEnd = System.currentTimeMillis();
            logger.info("Delete all customers TA ends");
            long queryTime = queryEnd - queryStart;

			em.flush();
			em.close();
			tm.commit();
			
			logger.info(objects.size()+" " + persistentClass.getSimpleName()+" successfully deleted in " + queryTime + " ms.");
			String writeToFile = new String(this.persistenceUnit.name() + " DELETE: " + objects.size() + " " + queryTime + "\n");
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
	}

	@Override
	public List<T> readObjects() {
		List<T> objectList = new ArrayList<T>();
		try {
			EntityManager em = emf.createEntityManager();

			String queryString = new String("SELECT x FROM " + this.persistentClass.getSimpleName() + " x");

			logger.info("Get all "+persistentClass.getSimpleName()+" TA begins");
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			Query q = em.createQuery(queryString);

			long queryStart = System.currentTimeMillis();

			objectList = q.getResultList();

			long queryEnd = System.currentTimeMillis();

			em.flush();
			em.close();
			tm.commit();

			logger.info("Get all "+persistentClass.getSimpleName()+" TA ends");

			long queryTime = queryEnd - queryStart;

			logger.info("Found " + objectList.size() + " " +persistentClass.getSimpleName()+" in " + queryTime + " ms.");

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
