package de.hsma.igt.flightsystem.tools;


public class Config {
	
	public static final String LOG = System.getProperty("user.dir") + "/logs/";
    public static final String LOG_STORAGE_LOCATION = "_crud_performance.txt";
    public static final Integer TRANSACTION_TIMEOUT = 9000000;
    public static final String PERSIST_STORAGE_LOCATION = System.getProperty("user.dir") + "/logs/performance.txt";
    public static final String PERSIST_STORAGE_OUTPUT_LOCATION = System.getProperty("user.dir") + "/logs/customers_out.txt";

}
