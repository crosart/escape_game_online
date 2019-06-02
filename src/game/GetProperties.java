package game;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

class GetProperties {

    /*
    size - The number of characters in the code
    tries - The maximum number of turns to discover the code
    devmode - Activates the debug mode or not
     */

    private static Logger logger = Logger.getLogger(Main.class.getName());
    private String size;
    private String tries;
    private String devmode;

    /*
    Retrieves the values from the config file
     */

    private void run() {

        try {

            Properties prop = new Properties();
            String propFile = "config.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFile);

            if (inputStream == null) {

                throw new Exception();

            }

            prop.load(inputStream);
            size = prop.getProperty("size");

            prop.load(inputStream);
            tries = prop.getProperty("tries");

            prop.load(inputStream);
            devmode = prop.getProperty("devmode");

        }
        catch (Exception e) {

            logger.info("Exception catch");

        }

    }

    String getSize() {
        this.run();
        return size;
    }

    String getTries() {
        this.run();
        return tries;
    }

    String getDevmode() {
        this.run();
        return devmode;
    }

}
