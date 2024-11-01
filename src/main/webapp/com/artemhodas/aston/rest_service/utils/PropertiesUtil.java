package com.artemhodas.aston.rest_service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    private PropertiesUtil() {
    }

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream resourceAsStream = PropertiesUtil.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (resourceAsStream == null) {
                throw new IOException();
            }
            PROPERTIES.load(resourceAsStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String get(String key) {

        return PROPERTIES.getProperty(key);
    }
}
