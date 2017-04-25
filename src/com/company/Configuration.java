package com.company;

/**
 * Created by igoru on 25-Apr-17.
 */
public class Configuration {
    private int portNumber = 8081;
    private int maxNumOfThreads = 200;
    private int timeout;

    public static final Configuration INSTANCE = new Configuration();

    public int getPortNumber() {
        return portNumber;
    }

    public int getMaxNumOfThreads() {
        return maxNumOfThreads;
    }

    public int getTimeout() {
        return timeout;
    }
}
