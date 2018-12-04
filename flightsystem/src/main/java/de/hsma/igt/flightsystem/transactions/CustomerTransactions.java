//package de.hsma.igt.flightsystem.transactions;
//
//import de.hsma.igt.flightsystem.models.*;
//import de.hsma.igt.flightsystem.tools.Config;
//
//import org.apache.log4j.Logger;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import javax.transaction.*;
//import java.util.Date;
//import java.util.HashSet;
//
//
//
//public class CustomerTransactions {
//    //accessing JBoss's Transaction can be done differently but this one works nicely
//    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
//
//    //build the EntityManagerFactory as you would build in in Hibernate ORM
//    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
//            Config.PERSISTENCE_UNIT_NAME);
//
//
//    private static Logger logger = Logger.getRootLogger();
//
//
//    public static void main(String[] args) {
//    	System.out.println(Config.PERSISTENCE_UNIT_NAME);
//    	
//    	// CUSTOMER
//        //Customer customer1 = new Customer("First", "Last", "Mail", new Date());
//        //CustomerAddress address1 = new CustomerAddress("Hauptstraße", "1", "12345", "Teststate", "Germany");
//
//        //FLIGHT
//        //Flight flight1 = new Flight();
//        //flight1.setPlaneType("TESTPLANE");
//        //FlightSegment segment1 = new FlightSegment("RH213", 15000);
//        //Airport airport1 = new Airport("FRA", "Frankfurt", 123.4f, 23.7f);
//        //Airport airport2 = new Airport("BER", "Berlin", 180.3f, 41.2f);
//        
//        
//        //CustomerController customerController = new CustomerController();
//        //customerController.createCustomers();
//
//
//        try {
//            tm.begin();
//
//            logger.info("TA begins");
//            EntityManager em = emf.createEntityManager();
//            	
//
//            
//            System.out.println("persist");
//            
//            //CUSTOMER
//            //em.persist(address1);
//            //customer1.setAddress(address1);
//            
//            //customer1.setStatus(CustomerStatus.GOLD);
//            
//            //em.persist(customer1);
//            
//            //CustomerPhone phone1 = new CustomerPhone(customer1, "12313231", PhoneType.HOME_PHONE);
//            //em.persist(phone1);
//            
//            
//            
//            //FLIGHT
//            //em.persist(airport1);
//            //em.persist(airport2);
//            
//            //em.persist(segment1);
//            HashSet<FlightSegment> segmentSet = new HashSet<>();
//            //segmentSet.add(segment1);
//            
//            HashSet<Customer> customerSet = new HashSet<>();
//            //customerSet.add(customer1);
//           
//            //em.persist(flight1);
//            
//            //FlightSeats flightSeat = new FlightSeats(30, 150, flight1, SeatType.BUSINESS);
//            //em.persist(flightSeat);
//            //em.persist(new Booking(customer1, flight1));
//            //System.out.println("persist FINSH");
//
//            System.out.println("flush");
//            em.flush();
//            System.out.println("flush FINISHED");
//            System.out.println("close");
//            em.close();
//            System.out.println("close FINISHED");
//
//            System.out.println("commit");
//            tm.commit();
//            System.out.println("commit FINISHED");
//            logger.info("successfully persisted.");
//
//            // customerController.deleteCustomer(1);
//            
//        } catch (NotSupportedException e) {
//            e.printStackTrace();
//        } catch (SystemException e) {
//            e.printStackTrace();
//        } catch (HeuristicMixedException e) {
//            e.printStackTrace();
//        } catch (HeuristicRollbackException e) {
//            e.printStackTrace();
//        } catch (RollbackException e) {
//            e.printStackTrace();
//        }
//        
//        System.out.println("FINSH");
//        System.exit(0);
//    }
//}
