package de.hsma.igt.flightsystem.tools;


public class Config {

    public static final Integer NUMBER_OF_CUSTOMERS = 100;
    public static final String PERSISTENCE_UNIT_NAME = PERSISTENCE_UNITS.OGM_MYSQL.name();
    public static final String LOG_STORAGE_LOCATION = "/home/eugen/igt/" + PERSISTENCE_UNIT_NAME + "_crud_performance.txt";
    public static final Integer TRANSACTION_TIMEOUT = 900000;
    public static final String PERSIST_STORAGE_LOCATION = "/home/eugen/igt/customers.txt";
    public static final String PERSIST_STORAGE_OUTPUT_LOCATION = "/home/eugen/igt/customers_out.txt";

}