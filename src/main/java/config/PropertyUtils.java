package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    public static Properties loadProperties(String propertyFile){
        Properties config = new Properties();
        InputStream input;
        try {
            input = PropertyUtils.class.getClassLoader().getResourceAsStream(propertyFile);
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}
