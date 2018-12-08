package de.hsma.igt.flightsystem.controllers;

import de.hsma.igt.flightsystem.models.Customer;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class CustomerController extends GenericController<Customer>{

	public CustomerController(PersistenceUnit persistenceUnit) {
		super(persistenceUnit, Customer.class);
	}
}
