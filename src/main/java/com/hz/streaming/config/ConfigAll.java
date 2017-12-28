package com.hz.streaming.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigAll implements Serializable{
    private static Logger logger= LoggerFactory.getLogger(ConfigAll.class);
    private static Properties properties;
    public static Map<String,String> configMap;

    public static void init(){
        InputStream is = ConfigAll.class.getClassLoader().getResourceAsStream("config.properties");
        properties=new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        configMap=new HashMap<String, String>((Map)properties);
    }



}
