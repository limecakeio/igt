package de.hsma.igt.flightsystem.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.hsma.igt.flightsystem.controllers.CustomerController;
import de.hsma.igt.flightsystem.controllers.IController;
import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.tools.CustomerPopulator;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;
import junit.extensions.RepeatedTest;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class TestScenario {

	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{ "igt_mysql", PersistenceUnit.OGM_MYSQL },
			{ "igt_mongodb", PersistenceUnit.OGM_MONGODB }, 
			{ "igt_redis", PersistenceUnit.OGM_REDIS },
			{ "igt_cassandra", PersistenceUnit.OGM_CASSANDRA } });
	}

	private PersistenceUnit persistenceUnit;

	public TestScenario(String container, PersistenceUnit persisenceUnit) {
		this.container = container;
		this.persistenceUnit = persisenceUnit;
	}

	private static String[] images;
	private static int index = 0;
	private String container;

	@BeforeClass
	// check up routine to make sure that no container at the start of the tests
	public static void stopAllContainer() {

		Process p = null;
		String s = null;
		String images = "";
		String delimiter = " ";

		try {
			// docker stop $(docker ps -aq does not work) does not work, so exceution in 2
			// steps
			p = Runtime.getRuntime().exec("docker ps -aq");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
				images += delimiter + s;
			}

			TestScenario.images = images.substring(images.indexOf(delimiter) + 1).split(delimiter);

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}

			Runtime.getRuntime().exec("docker stop" + images);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Before
	public void startSingleContainer() {
		Process p = null;
		String s = null;

		try {
			p = Runtime.getRuntime().exec("docker start " + container);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			while ((s = stdError.readLine()) != null) {
				System.err.println(s);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@After
	// this method is to be called of every single test
	public void stopSingleContainer() {
		Process p = null;
		String s = null;

		try {
			p = Runtime.getRuntime().exec("docker stop " + container);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			while ((s = stdError.readLine()) != null) {
				System.err.println(s);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void populateCustomer() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IController<Customer> cc = new CustomerController(persistenceUnit);
		List<Customer> cl = new CustomerPopulator().populateAsList(10);

		cc.createObjects(cl);

		List<Customer> dbcl = cc.readObjects();

		assertEquals(cl.size(), dbcl.size());

	}

}
