package de.hsma.igt.flightsystem.transactions;

import de.hsma.igt.flightsystem.tools.PersistenceUnit;

public class DatabasePopulatorTest {

	public static void main(String[] args) {
		
//		for (PersistenceUnit persistenceUnit : PersistenceUnit.values()) {
//			DatabasePopulator p = new DatabasePopulator(persistenceUnit);
//			p.populateDatabase();
//		}
		
		DatabasePopulator p = new DatabasePopulator(PersistenceUnit.OGM_MONGODB);
		p.populateDatabase();

	}

}
