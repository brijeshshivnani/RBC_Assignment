package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

	private static File file;
	String filePath = "src//main//resources/";
	public PropertyLoader() {}

	public String getProperty(String fileName, String propertyName) {
		Properties prop = new Properties();
	       
			FileInputStream fileInput = null;
			filePath = "src//main//resources/" + fileName;
			
			System.out.println("file path is--->" +filePath);
			file = new File(filePath);
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			//load properties file
			try {
				prop.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}
        String value = prop.getProperty(propertyName);
        System.out.println("---->" + value);
        if(value == null) {
//            LOGGER.warning("propertyName (" + propertyName + ") not found in property file (" + FULL_PATH + ")");
        }

        return value;
    }
}
