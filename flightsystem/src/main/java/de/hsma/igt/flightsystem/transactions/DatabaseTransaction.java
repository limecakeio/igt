package de.hsma.igt.flightsystem.transactions;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

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

import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.tools.FlightPopulator;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class DatabaseTransaction {
	
	//accessing JBoss's Transaction can be done differently but this one works nicely
    private TransactionManager tm;

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    private EntityManagerFactory emf;


    private static Logger logger = Logger.getRootLogger();
    
    public DatabaseTransaction(PersistenceUnit databaseUnit) {
    	emf = Persistence.createEntityManagerFactory(databaseUnit.name());
    	tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    }
    
    
    public void doTransactions() {
    	doFlightTransaction();
    }


	private void doFlightTransaction() {
		List<Flight> flights = FlightPopulator.populateFlightsAsList(0);
		

        try {
            tm.begin();

            logger.info("TA begins");
            EntityManager em = emf.createEntityManager();
            
            for (Flight flight : flights)
            	em.persist(flight);
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
        
        System.out.println("FINSH");
	}
}
