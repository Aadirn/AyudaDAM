/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patronesabstractfactorypatternexample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author castelao
 */
public class Config {

    private static Properties config;

    static {
        config = new Properties();

        try (InputStream input
                = Config.class.getResourceAsStream("/resources/config.properties")) {
            
            config.load(input);

        } catch (IOException ex) {
            System.err.println("Error leyendo config. " + ex.getMessage());
        }
    }
    
    public Config() {
    }

    public static String getProperty(String name) {
        return config.getProperty(name);
    }
    
    public static int getIntProperty(String name) {
        return Integer.parseInt(config.getProperty(name));
    }

}
