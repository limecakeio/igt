package de.hsma.igt.flightsystem.models;

import de.hsma.igt.flightsystem.controllers.IController;
import de.hsma.igt.flightsystem.tools.PersistenceUnit;

/**
 * TESTS HELPER
 *
 * In rebellion against OOP, here are some functional methods to make testing a lot easier.
 * */

public class TestsHelper {
    public static final String DB_ARG_NAME = "db";
    public static final String ERROR_NO_DB_ARG = "Tests require the argument \"db\" to specify a database, in order to run.";
    public static final String ERROR_INVALID_DB_ARG = "Cannot find database specified by arg \"db\".";
    public static final String PU_PREFIX = "OGM_";


    /**
     * Resolves the controller for a database based on the name from arguments passed to testing environment.
     *
     * Freaks out if no persistence unit exist.
     */
    public static PersistenceUnit resolvePersistenceUnit() {
        PersistenceUnit pu;
        String dbName = getDBName();
        String puName = PU_PREFIX + dbName;

        try {
            pu = PersistenceUnit.valueOf(puName);
        } catch (IllegalArgumentException e) {
            throw new Error(ERROR_INVALID_DB_ARG);
        }

        return pu;
    }

    /**
     * Attempts to retrieve the database name from the system and, if successful, returns it in UPPERCASE
     *
     * Freaks out if the name hasn't been set
     */
    public static String getDBName() {
        //Resolve DB via arg
        if (System.getProperty(DB_ARG_NAME) == null) throw new Error(ERROR_NO_DB_ARG);
        return System.getProperty(DB_ARG_NAME).toUpperCase();
    }

    /**
     * Logs the time taken by a particular test using a particular DB
     * */
    public static void logTimeTaken(String testName, long startTimestamp, long endTimestamp) {
        System.out.println(testName + " -> elpased time:" + (endTimestamp - startTimestamp)/1000 + " ms using " + getDBName());

    }


}
