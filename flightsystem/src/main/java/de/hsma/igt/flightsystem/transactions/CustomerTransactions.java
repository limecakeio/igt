package de.hsma.igt.flightsystem.transactions;

import de.hsma.igt.flightsystem.models.Airport;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.CustomerAddress;
import de.hsma.igt.flightsystem.models.CustomerPhone;
import de.hsma.igt.flightsystem.models.CustomerStatus;
import de.hsma.igt.flightsystem.models.Flight;
import de.hsma.igt.flightsystem.models.FlightSeats;
import de.hsma.igt.flightsystem.models.Flightsegment;
import de.hsma.igt.flightsystem.models.PhoneType;
import de.hsma.igt.flightsystem.models.SeatType;
import de.hsma.igt.flightsystem.tools.Config;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by jenskohler on 06.12.17.
 */
public class CustomerTransactions {
    //accessing JBoss's Transaction can be done differently but this one works nicely
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            Config.PERSISTENCE_UNIT_NAME);


    private static Logger logger = Logger.getRootLogger();


    public static void main(String[] args) {
    	System.out.println(Config.PERSISTENCE_UNIT_NAME);
    	
    	// CUSTOMER
        Customer customer1 = new Customer("First", "Last", "Mail", new Date());
        CustomerAddress address1 = new CustomerAddress("Hauptstraße", "1", "12345", "Teststate", "Germany");
        CustomerStatus status1 = new CustomerStatus("WHITE_GOLD");
        PhoneType phoneType1 = new PhoneType("HOME");

        //FLIGHT
        Flight flight1 = new Flight();
        flight1.setPlaneType("TESTPLANE");
        SeatType seatType1 = new SeatType("Economy");
        Flightsegment segment1 = new Flightsegment("RH213", 15000);
        Airport airport1 = new Airport(7,"FRA", 123.4f, 23.7f);
        Airport airport2 = new Airport(8,"BER", 180.3f, 41.2f);
        
        //CustomerController customerController = new CustomerController();
        //customerController.createCustomers();


        try {
            tm.begin();

            logger.info("TA begins");
            EntityManager em = emf.createEntityManager();
            	

            
            System.out.println("persist");
            
            //CUSTOMER
            em.persist(address1);
            customer1.setAddress(address1);
            
            em.persist(status1);
            customer1.setStatus(status1);
            
            em.persist(customer1);
            
            em.persist(phoneType1);
            CustomerPhone phone1 = new CustomerPhone(customer1, "12313231", phoneType1);
            em.persist(phone1);
            
            
            
            //FLIGHT
            em.persist(airport1);
            em.persist(airport2);
            segment1.setDestination(airport1);
            segment1.setOrigin(airport2);
            
            em.persist(segment1);
            HashSet<Flightsegment> segmentSet = new HashSet<>();
            segmentSet.add(segment1);
             
            flight1.setFlightsegments(segmentSet);
            
            HashSet<Customer> customerSet = new HashSet<>();
            customerSet.add(customer1);
            flight1.setCustomer(customerSet);
           
            em.persist(flight1);
            
            em.persist(seatType1);
            
            FlightSeats flightSeat = new FlightSeats(30, 150, flight1, seatType1);
            em.persist(flightSeat);
            System.out.println("persist FINSH");



            System.out.println("flush");
            em.flush();
            System.out.println("flush FINISHED");
            System.out.println("close");
            em.close();
            System.out.println("close FINISHED");

            System.out.println("commit");
            tm.commit();
            System.out.println("commit FINISHED");
            logger.info("successfully persisted.");

            // customerController.deleteCustomer(1);
            
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
        System.exit(0);
    }
}
