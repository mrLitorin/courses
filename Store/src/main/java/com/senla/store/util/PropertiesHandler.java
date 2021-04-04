package com.senla.store.util;

import com.senla.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class PropertiesHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHandler.class.getSimpleName());
    private static Properties properties;
    private static final String PATH_TO_PROPERTIES = "Store/src/main/resources/app.properties";

    public PropertiesHandler() {
    }

    private static void loadProperties() {
        try (InputStream inputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("e");
            throw new ServiceException("Filed to read file: " + PATH_TO_PROPERTIES);
        }
    }

    public static Optional<String> getProperties(String key) {
        if (properties == null) {
            loadProperties();
        }
        return Optional.ofNullable(properties.getProperty(key));
    }
}
