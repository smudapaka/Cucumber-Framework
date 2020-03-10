package reusableActions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	
	 private Properties prop;
	 private final String propertyFilePath= "config//Configuation.properties";
	 
	 
	 public ConfigReader(){
		 BufferedReader reader;
		 try {
		 reader = new BufferedReader(new FileReader(propertyFilePath));
		 prop = new Properties();
		 try {
			 prop.load(reader);
		     reader.close();
		 } catch (IOException e) {
		    e.printStackTrace();
		 }
		 } catch (FileNotFoundException e) {
		    e.printStackTrace();
		 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		 } 
	 }
	 
	 public String getChromeDriverPath(){
		 String driverPath = prop.getProperty("ChromDriverPath");
		 if(driverPath!= null) return driverPath;
		 else throw new RuntimeException("driverPath not specified in the Configuration.properties file."); 
	 }
	 
	 public String getIEDriverPath(){
		 String driverPath = prop.getProperty("IEDriverpath");
		 if(driverPath!= null) return driverPath;
		 else throw new RuntimeException("driverPath not specified in the Configuration.properties file."); 
	 }
	 
	 public long getImplicitlyWait() { 
		 String implicitlyWait = prop.getProperty("implicitlyWait");
		 if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		 else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
	 }
	 
	 public String getQAEnvironment() {
		 String url = prop.getProperty("QA");
		 if(url != null) return url;
		 else throw new RuntimeException("url not specified in the Configuration.properties file.");
	 }
	 
	 public String getsynovusEnvironment() {
		 String url = prop.getProperty("Synovus");
		 if(url != null) return url;
		 else throw new RuntimeException("url not specified in the Configuration.properties file.");
	 }
	 
	 public String getInforEnvironment() {
		 String url = prop.getProperty("MYSYNOVUS");
		 if(url != null) return url;
		 else throw new RuntimeException("url not specified in the Configuration.properties file.");
	 }
	 
	 
}
