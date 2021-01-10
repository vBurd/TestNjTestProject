package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public abstract class AbstractHelper {
    protected static Logger logger;
    protected static Properties properties;
    protected static String baseUrl;

    public AbstractHelper() {
        logger  = Logger.getLogger(String.valueOf(this.getClass()));
        properties = new Properties();
        try {
            properties.load(new FileInputStream("application.properties"));
        } catch (IOException ex) { ;
            ex.printStackTrace();
        }
        baseUrl = properties.getProperty("deck.of.card.url");
    }
//
}
