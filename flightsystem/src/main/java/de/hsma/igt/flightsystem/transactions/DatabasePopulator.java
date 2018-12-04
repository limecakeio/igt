
package de.hsma.igt.flightsystem.transactions;

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

import de.hsma.igt.flightsystem.controllers.AirportController;
import de.hsma.igt.flightsystem.controllers.CustomerController;
import de.hsma.igt.flightsystem.controllers.FlightController;
import de.hsma.igt.flightsystem.controllers.IController;
import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.tools.AirportPopulator;
import de.hsma.igt.flightsystem.tools.CustomerPopulator;
import de.hsma.igt.flightsystem.tools.FlightPopulator;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;
import de.hsma.igt.flightsystem.tools.Populator;

public class DatabasePopulator {

	PersistenceUnit persistenceUnit;

    private static Logger logger = Logger.getRootLogger();
    
    public DatabasePopulator(PersistenceUnit databaseUnit) {
    	persistenceUnit = databaseUnit;
    }
    
    
    public void populateDatabase() {
  // 	populate(new FlightController(), new FlightPopulator().populateAsList(10));

    	CustomerController cc = new CustomerController();
    	populate(cc, new CustomerPopulator().populateAsList(10));
    	List<Customer> cl =  cc.readObjects();
    	for(Customer customer : cl) {
		System.out.println(customer.getEmail());
	}
    	//populate(new AirportController(), new AirportPopulator().getAirports());
//    	for(Airport airport : new AirportController().readObjects()) {
//    		System.out.println(airport.getName());
//    	}
    }


	private void populate(IController controller, List entities) {
      

            logger.info(persistenceUnit + " TA begins");
            controller.createObjects(entities);
            
        
        System.out.println(persistenceUnit + "FINSH");
	}
}

