package com.testvagrant.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader(System.getProperty("user.dir")+"//src//test//resources//config.properties");
    }
    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUri(){
        String baseUri = properties.getProperty("baseUri");
        if(baseUri != null) return baseUri;
        else throw new RuntimeException("Base URI key is not specified in config.properties file.");
    }
}
