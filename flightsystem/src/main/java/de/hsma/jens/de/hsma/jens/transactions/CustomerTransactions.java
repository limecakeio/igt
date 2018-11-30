package de.hsma.jens.de.hsma.jens.transactions;

import de.hsma.jens.controllers.CustomerController;
import de.hsma.jens.models.Customer;
import de.hsma.jens.tools.Config;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.util.Date;
import java.util.Set;

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
        Customer customer1 = new Customer();

        customer1.setC_ADDR_ID(1);
        customer1.setC_BALANCE(11.11);
        customer1.setC_BIRTHDATE(new Date());
        customer1.setC_DATA("data_11");
        customer1.setC_DISCOUNT(11.11);
        customer1.setC_EMAIL("email_11");
        customer1.setC_EXPIRATION(new Date());
        customer1.setC_FNAME("fname_11");
        customer1.setC_ID(100000000);
        customer1.setC_LAST_LOGIN(new Date());
        customer1.setC_LOGIN(new Date());
        customer1.setC_PASSWD("password_11");
        customer1.setC_LNAME("lname_11");
        customer1.setC_PHONE("phone_11");
        customer1.setC_SINCE(new Date());
        customer1.setC_YTD_PMT(11.11);
        customer1.setC_UNAME("uname_11");
//
//
//        CustomerController customerController = new CustomerController();
//        customerController.createCustomers();


        try {
            tm.begin();

            logger.info("TA begins");
            EntityManager em = emf.createEntityManager();


            
            System.out.println("persist");
            em.persist(customer1);
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
