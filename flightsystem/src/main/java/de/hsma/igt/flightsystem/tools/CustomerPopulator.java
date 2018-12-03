package de.hsma.igt.flightsystem.tools;


import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.models.CustomerAddress;
import de.hsma.igt.flightsystem.models.CustomerPhone;
import de.hsma.igt.flightsystem.models.PhoneType;
import org.neo4j.cypher.internal.frontend.v2_3.ast.functions.Rand;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

import static de.hsma.igt.flightsystem.tools.RandomGenerator.*;


public class CustomerPopulator {

    public static List<Customer> populateCustomerAsList(int numberOfCustomers) {
        List<Customer> cList = new ArrayList<Customer>();

        String C_LNAME, C_FNAME;
        String C_EMAIL, C_PHONE_1, C_PHONE_2;
        String C_ADD_STREET, C_ADD_STREET_NO, C_ADD_POSTCODE, C_ADD_STATE, C_ADD_COUNTRY;
        java.sql.Date C_BIRTHDATE;

        try {

            for (int i = 1; i <= numberOfCustomers; i++) {
                Customer myCustomer = new Customer();

                if (i % 10000 == 0)
                    System.out.print(i / 10000 + " ");
                C_LNAME = getRandomAString(8, 15);
                C_FNAME = getRandomAString(8, 15);

                C_EMAIL = C_FNAME + "@" + getRandomAString(2, 9) + ".com";
                C_PHONE_1 = getRandomNString(8, 15) + "";
                C_PHONE_2 = getRandomNString(8, 15) + "";

                C_ADD_STREET = getRandomAString(8, 15) + " " + getRandomAString(0, 6);
                C_ADD_STREET_NO = getRandomNString(1, 4) + "";
                C_ADD_POSTCODE = getRandomNString(5) + "";
                C_ADD_STATE = getRandomAString(5, 10) + " " + getRandomAString(0, 10);
                C_ADD_COUNTRY = getRandomAString(5, 15) + " " + getRandomAString(0, 10);


                GregorianCalendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_YEAR, -1 * getRandomInt(1, 730));
                cal.add(Calendar.DAY_OF_YEAR, getRandomInt(0, 60));
                if (cal.after(new GregorianCalendar()))
                    cal = new GregorianCalendar();

                cal = new GregorianCalendar();
                cal.add(Calendar.HOUR, 2);

                int year = getRandomInt(1880, 2000);
                int month = getRandomInt(0, 11);
                int maxday = 31;
                int day;
                if (month == 3 | month == 5 | month == 8 | month == 10)
                    maxday = 30;
                else if (month == 1)
                    maxday = 28;
                day = getRandomInt(1, maxday);
                cal = new GregorianCalendar(year, month, day);
                C_BIRTHDATE = new java.sql.Date(cal.getTime().getTime());


                try {// Set parameter
                    //Compose an address
                    CustomerAddress ca = new CustomerAddress();
                    ca.setStreetname(C_ADD_STREET);
                    ca.setStreetnumber(C_ADD_STREET_NO);
                    ca.setPostcode(C_ADD_POSTCODE);
                    ca.setState(C_ADD_STATE);
                    ca.setCountry(C_ADD_COUNTRY);
                    myCustomer.setAddress(ca);

                    //Setup some phones
                    CustomerPhone cp1 = new CustomerPhone(myCustomer, C_PHONE_1, PhoneType.HOME_PHONE);
                    CustomerPhone cp2 = new CustomerPhone(myCustomer, C_PHONE_2, PhoneType.MOBILE_PHONE);
                    Set<CustomerPhone> cPhones = new HashSet<>();
                    cPhones.add(cp1);
                    cPhones.add(cp2);
                    myCustomer.setContactNumbers(cPhones);

                    myCustomer.setDateOfBirth(C_BIRTHDATE);
                    myCustomer.setEmail(C_EMAIL);
                    myCustomer.setFirstname(C_FNAME);
                    myCustomer.setLastname(C_LNAME);

                    cList.add(myCustomer);


                } catch (java.lang.Exception ex) {
                    System.err.println("Unable to populate CUSTOMER table");
                    System.out.println("C_ID=" + i +
                            " C_FNAME=" + C_FNAME +
                            " C_LNAME=" + C_LNAME +
                            " C_ADD_STREET=" + C_ADD_STREET +
                            " C_ADD_STREET_NO=" + C_ADD_STREET_NO +
                            " C_ADD_POSTCODE=" + C_ADD_POSTCODE +
                            " C_ADD_STATE=" + C_ADD_STATE +
                            " C_ADD_COUNTRY=" + C_ADD_COUNTRY +
                            " C_PHONE_1=" + C_PHONE_1 +
                            " C_PHONE_2=" + C_PHONE_2 +
                            " C_EMAIL=" + C_EMAIL +
                            " C_BIRTHDATE=" + C_BIRTHDATE);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            System.out.print("\n");
            ////con.commit();
        } catch (java.lang.Exception ex) {
            System.err.println("Unable to populate CUSTOMER table");
            ex.printStackTrace();
            System.exit(1);
        }

        return cList;
    }
}

