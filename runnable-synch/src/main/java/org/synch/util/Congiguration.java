package org.synch.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Congiguration {

    private static Properties configuration;

    public static Properties getConfiguration() {
        return configuration;
    }

    static {
        configuration = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("config.properties")) {
            configuration.load(resourceStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
