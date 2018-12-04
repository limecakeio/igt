/*
package de.hsma.igt.flightsystem.transactions;

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

import de.hsma.igt.flightsystem.controllers.CustomerController;
import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.tools.CustomerPopulator;
import de.hsma.igt.flightsystem.tools.FlightPopulator;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;
import de.hsma.igt.flightsystem.tools.Populator;

public class DatabasePopulator {
	
	//accessing JBoss's Transaction can be done differently but this one works nicely
    private TransactionManager tm;

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    private EntityManagerFactory emf;
    
    PersistenceUnit persistenceUnit;


    private static Logger logger = Logger.getRootLogger();
    
    public DatabasePopulator(PersistenceUnit databaseUnit) {
    	persistenceUnit = databaseUnit;
    	emf = Persistence.createEntityManagerFactory(databaseUnit.name());
    	tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    }
    
    
    public void populateDatabase() {
    	populate(new FlightController, new FlightPopulator().populateAsList(10));
    	populate(new CustomerController(), new CustomerPopulator().populateAsList(10));
    }


	private void populate(Controller controller, List entities) {
        try {
            tm.begin();

            logger.info(persistenceUnit + " TA begins");
            controller.createCustomers(entities);
            
            
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
        
        System.out.println(persistenceUnit + "FINSH");
	}
}
*/
