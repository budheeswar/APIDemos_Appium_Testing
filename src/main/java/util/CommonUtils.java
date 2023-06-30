package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {

	public Properties getConfigFile() {
		
		FileInputStream file = null;
		Properties properties = new Properties();
		String dir=System.getProperty("user.dir");
		try {
	        // Provide the path to your properties file
	        file = new FileInputStream(dir+"/src/main/resources/config.properties");
	        properties.load(file);

	        // Access values using the key
	        String value = properties.getProperty("browserName");
	        System.out.println(value);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the input stream
	        if (file != null) {
	            try {
	                file.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		return properties;
	}
	}
