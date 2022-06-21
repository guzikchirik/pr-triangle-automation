package io.github.guzikchirik.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    private static Properties properties;

    private PropertiesManager() {
        getProperties();
    }

    public static String getProperty(final String key) {
        return getProperties().getProperty(key);
    }

    public static Properties getProperties() {
        if (properties == null || properties.isEmpty()) {
            try (InputStream input = new FileInputStream("src/main/resources/test.properties")) {

                properties = new Properties();
                properties.load(input);
                return properties;

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            return properties;
        }
        return new Properties();
    }
}
