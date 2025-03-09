package org.graber.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Congiguration {

    private static Properties configuration;

    private static ExecutorService executor;

    public static Properties getConfiguration() {
        return configuration;
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    public static void executorShutdown() {
        executor.shutdown();
    }

    static {
        configuration = new Properties();
        executor = Executors.newCachedThreadPool();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream resourceStream = loader.getResourceAsStream("config.properties")) {
            configuration.load(resourceStream);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
