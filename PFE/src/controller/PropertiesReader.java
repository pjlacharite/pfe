package controller;

import java.io.IOException;
import java.util.Properties;

import android.util.Log;

/**
 * Class to read properties file
 * @author pjlacharite
 *
 */
public class PropertiesReader {
    
    /**
     * Reads a properties file
     * @param fileName
     * @return
     */
    public Properties getProperties(String fileName){
        Properties properties = new Properties();
        try {
            properties.load(PropertiesReader.class.getClassLoader().getResourceAsStream(fileName));
            return properties;
        } catch (IOException e) {
            Log.e("PropertiesReader".toString(), e.getMessage());
            return null;
        }
    }
}
