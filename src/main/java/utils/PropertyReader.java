package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private Properties props = new Properties();

    public Properties getProperty() throws Exception {
        InputStream inputStream = null;
        String propertyFileName = "config.properties";
        if (props.isEmpty()) {
            try {
                inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
                props.load(inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        return props;
    }
}
