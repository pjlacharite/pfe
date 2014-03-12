package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import utils.PropertiesReader;
import android.util.Log;

/**
 * Holds a list of instantiated controller fetched from a properties file and dispatches them to the needing classes
 * @author pjlacharite
 *
 */
public class ControllerDispatcher {
    private Map<String, Controller> controllerMap = null;
    private static ControllerDispatcher instance = null;
    private static final String PROPERTIES_FILENAME = "controllers.properties";

    /**
     * Constructor for ControlerDispatcher
     */
    private ControllerDispatcher(){
        controllerMap = new HashMap<String, Controller>();
        Properties controllerProperties = new PropertiesReader().getProperties(PROPERTIES_FILENAME);
        if (controllerProperties == null)
            Log.e(this.getClass().toString(), "controllerProperties NULL");
        for(String key : controllerProperties.stringPropertyNames()){
            String className = controllerProperties.getProperty(key);
            Class<?> controllerClass;
            Controller newController;
            try {
                controllerClass = Class.forName(className);
                if (controllerClass == null)
                    Log.e(this.getClass().toString(), "controllerClass NULL");
                newController = (Controller)controllerClass.newInstance();
                if (newController == null)
                    Log.e(this.getClass().toString(), "newController NULL");
                controllerMap.put(key, newController);
            } catch (InstantiationException e) {
                Log.e(this.getClass().toString(), e.getLocalizedMessage());
            } catch (IllegalAccessException e) {
                Log.e(this.getClass().toString(), e.getLocalizedMessage());
            } catch (ClassNotFoundException e) {
                Log.e(this.getClass().toString(), e.getLocalizedMessage());
            }
        }
    }

    /**
     * Singleton getter for instance
     * @return
     */
    public static ControllerDispatcher getDispatcher(){
        if (instance == null){
            instance = new ControllerDispatcher();
        }
        return instance;
    }
    
    /**
     * Getter to retrieve a Controller instance from its classname
     * @param className
     * @return
     */
    public Controller getController(String className){
        return controllerMap.get(className);
    }
}
