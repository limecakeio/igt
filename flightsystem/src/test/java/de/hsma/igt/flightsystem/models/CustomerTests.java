package de.hsma.igt.flightsystem.models;

import com.google.common.collect.Lists;
import de.hsma.igt.flightsystem.controllers.CustomerController;
import de.hsma.igt.flightsystem.controllers.IController;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

public class CustomerTests {

    List<IController> customerControllers;
    Customer customer;

    @Before
    public void setupControllers() {
        customerControllers = new ArrayList();
        for(PersistenceUnit dbType : PersistenceUnit.values()) {
            customerControllers.add(new CustomerController(dbType));
        }
    }

    @Before
    public void initCustomer() {

    }

    @Test
    public void testCreateRetrieveCustomer() {

        //Persist customer into every db
        for (IController ct : customerControllers) {
            customer = new Customer("Peter", "Patter", "petterpats@patty.com", new Date(28022018));

            //Setup some phones
            CustomerPhone homePh = new CustomerPhone(customer, "040 668876", PhoneType.HOME_PHONE);
            CustomerPhone mobilePh = new CustomerPhone(customer, "0151 987 6543 223", PhoneType.MOBILE_PHONE);
            Set<CustomerPhone> phones = new HashSet<>();
            phones.add(homePh);
            phones.add(mobilePh);
            customer.setContactNumbers(phones);

            //Setup an address
            CustomerAddress customerAddy = new CustomerAddress("Kottwitzstr.", "66", "20253", "Hamburg", "Germany");
            customer.setAddress(customerAddy);

            //Set an initial status
            customer.setStatus(CustomerStatus.NONE);

            ct.createObjects(Lists.newArrayList(customer));
        }

        //Read customer from every DB
        for (IController ct : customerControllers) {
            List<Customer> customers = ct.readObjects();
            //Test record count is correct
            Assert.assertEquals(customers.size(), 1);
            //Grab the customer and validate record
            Customer retrievedCustomer = customers.get(0);

            //Customer details
            Assert.assertEquals(retrievedCustomer.getFirstname(), customer.getFirstname());
            Assert.assertEquals(retrievedCustomer.getLastname(), customer.getLastname());
            Assert.assertEquals(retrievedCustomer.getEmail(), customer.getEmail());
            //Assert.assertEquals(retrievedCustomer.getDateOfBirth(), customer.getDateOfBirth());
        }

    }
}
