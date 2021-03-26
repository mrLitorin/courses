package com.senla.bookshop.util;

import exception.ServiceException;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class PropertiesHandler {
    private static final Logger LOGGER = Logger.getLogger(PropertiesHandler.class.getSimpleName());
    private static Properties properties;
    private static final String PATH_TO_PROPERTIES = "main/src/main/resources/app.properties";

    public PropertiesHandler() {
    }

    private static void loadProperties() {
        try (InputStream inputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new ServiceException("Filed to read file: " + PATH_TO_PROPERTIES);
        }
    }

    public static Optional<String> getProperties(String key){
        if (properties == null){
            loadProperties();
        }
        return Optional.ofNullable(properties.getProperty(key));
    }
}
