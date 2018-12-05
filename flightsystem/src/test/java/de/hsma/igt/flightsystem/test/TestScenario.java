package de.hsma.igt.flightsystem.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.hsma.igt.flightsystem.controllers.CustomerController;
import de.hsma.igt.flightsystem.controllers.IController;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.tools.CustomerPopulator;

public class TestScenario {
	
	@BeforeClass
	public static void startContainer() {
		try {
			Runtime.getRuntime().exec("docker start igt_mysql");
		} catch (IOException e) {
			System.err.println("Could not start container");
			e.printStackTrace();
		}
	}
	
	@Test
	public void populateCustomer() {
    	IController cc = new CustomerController();
    	List<Customer> cl = new CustomerPopulator().populateAsList(10);
    	
    	cc.createObjects(cl);
    	
    	List<Customer> dbcl = cc.readObjects();
    	
    	assertEquals(cl.size(), dbcl.size());
    	System.out.println("Finish");
		
	}

}
