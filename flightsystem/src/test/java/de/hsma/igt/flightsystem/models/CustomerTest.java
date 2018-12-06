package de.hsma.igt.flightsystem.models;

import com.google.common.collect.Lists;
import de.hsma.igt.flightsystem.controllers.CustomerController;
import de.hsma.igt.flightsystem.controllers.IController;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;

//We want the create to run first!
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {
    @Rule public TestName name = new TestName();

    public static final String testName = "Customer Tests";
    private IController customerController = new CustomerController(TestsHelper.resolvePersistenceUnit());
    private Customer customer = new Customer("Peter", "Patter", "petterpats@patty.com", new Date(28021986));
    private long startTimestamp;
    private SimpleDateFormat bdayFormatter = new SimpleDateFormat("dd-MM-yyyy");

    @BeforeClass
    public static void setup() {

    }

    @Before
    public void beforeEvery() {
        startTimestamp = System.nanoTime();
    }

    @After
    public void afterEvery() {
        long endTimestamp = System.nanoTime();
        TestsHelper.logTimeTaken(name.getMethodName(), startTimestamp, endTimestamp);
    }

    /**
     * CUSTOMER CRUD
     */

    @Test
    public void test0CreateCustomer() {
        List<Customer> customers = customerController.readObjects();
        int initSize = customers.size();

        //Make the customer reachable
        CustomerPhone homePh = new CustomerPhone(customer, "040 668876", PhoneType.HOME_PHONE);
        CustomerPhone mobilePh = new CustomerPhone(customer, "0151 987 6543 223", PhoneType.MOBILE_PHONE);
        Set<CustomerPhone> phones = new HashSet<>();
        phones.add(homePh);
        phones.add(mobilePh);
        customer.setContactNumbers(phones);

        //Give the customer a place to live
        CustomerAddress customerAddy = new CustomerAddress("Kottwitzstr.", "66", "20253", "Hamburg", "Germany");
        customer.setAddress(customerAddy);

        customerController.createObjects(Lists.newArrayList(customer));

        //Test record count has increased
        Assert.assertTrue(customerController.readObjects().size() > initSize);
    }

    @Test
    public void testReadCustomer() {
        List<Customer> customers = customerController.readObjects();
        //Grab the customer and validate record
        Customer customerRecord = customers.get(0);
        //Customer details
        Assert.assertEquals(customerRecord.getFirstname(), customer.getFirstname());
        Assert.assertEquals(customerRecord.getLastname(), customer.getLastname());
        Assert.assertEquals(customerRecord.getEmail(), customer.getEmail());
        Assert.assertEquals(bdayFormatter.format(customerRecord.getDateOfBirth()), bdayFormatter.format(customer.getDateOfBirth()));

    }

    @Test
    public void testUpdateCustomerPhone() {
        Set<CustomerPhone> numbers = customer.getContactNumbers();
        numbers.add(new CustomerPhone(customer, "01222344", PhoneType.WORK_PHONE));
        customer.setContactNumbers(numbers);
        customerController.updateObjects(Lists.newArrayList(customer));

        List<Customer> customers = customerController.readObjects();
        Customer customerRecord = customers.get(0);

        Assert.assertTrue(customerRecord.getContactNumbers().size() > customer.getContactNumbers().size());
    }
}
