package de.hsma.igt.flightsystem.controllers;

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

import de.hsma.igt.flightsystem.models.BaseEntity;
import de.hsma.igt.flightsystem.tools.Config;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public abstract class GenericController<T extends BaseEntity> implements IController<T> {

	private Class<T> persistentClass;
	private static Logger logger = Logger.getRootLogger();
	// accessing JBoss's Transaction can be done differently but this one works
	// nicely
	TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
	// build the EntityManagerFactory as you would build in in Hibernate ORM
	EntityManagerFactory emf = null;
	
	@SuppressWarnings("unchecked")
	public GenericController(PersistenceUnit persistenceUnit, Class<T> persistentClass) {
		emf = Persistence.createEntityManagerFactory(persistenceUnit.name());
		this.persistentClass = persistentClass;
	}
	
	@Override
	public void createObjects(List<T> objects) {
		try {
			EntityManager em = emf.createEntityManager();
			// tm.setTransactionTimeout(seconds);
			tm.begin();
			for (T objecet : objects) {
				em.persist(objecet);
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
	public void updateObjects(List<T> objects) {
		try {
	           logger.info("Update customer TA begins");
	           EntityManager em = emf.createEntityManager();
	           tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
	           tm.begin();


	           //long queryStart = System.currentTimeMillis();
	           for (T object : objects) {
	        	   T objectToUpdate = (T)em.find(object.getClass(), object.getId());
	        	   objectToUpdate = object;
	        	   em.merge(objectToUpdate);
	           }
	           //long queryEnd = System.currentTimeMillis();


	           em.flush();
	           em.close();
	           tm.commit();
	           logger.info("Update customer TA ends");

	           //long queryTime = queryEnd - queryStart;

	           //logger.info("Customer successfully persisted in " + queryTime + " ms.");

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
	public void deleteObjects(List<T> objects) {
		try {
            //logger.info("Delete customer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();


            // long queryStart = System.currentTimeMillis();
            for(T object : objects) {
            	T objectToDelete= (T) em.find(object.getClass(), object.getId());
            	em.remove(objectToDelete);
            }
            // logger.info("Found customer: " + cust.toString());
            // logger.info("Deleting customer...");

            //  long queryEnd = System.currentTimeMillis();


            em.flush();
            em.close();
            tm.commit();
            //logger.info("Delete customer TA ends");

            //  long queryTime = queryEnd - queryStart;

            // logger.info("Customer successfully deleted in " + queryTime + " ms.");


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
	public List<T> readObjects() {
		List<T> objectList = new ArrayList<T>();
		try {
			EntityManager em = emf.createEntityManager();

			String queryString = new String("SELECT x FROM "+ this.persistentClass.getSimpleName()+ " x");
			
			logger.info("Get all customer TA begins");
			tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
			tm.begin();

			Query q = em.createQuery(queryString);

			long queryStart = System.currentTimeMillis();

			objectList = q.getResultList();

			long queryEnd = System.currentTimeMillis();

			em.flush();
			em.close();
			tm.commit();

			logger.info("Get all customer TA ends");

			long queryTime = queryEnd - queryStart;

			logger.info("Found " + objectList.size() + " customers in " + queryTime + " ms.");

//			String writeToFile = new String(
//					Config.PERSISTENCE_UNIT_NAME + " READ  : " + cust.size() + " " + queryTime + "\n");
//			Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);

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
//			catch (IOException e) {
//			e.printStackTrace();
//		}
		return objectList;
	}

	
}
