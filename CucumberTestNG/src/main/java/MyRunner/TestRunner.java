package MyRunner;


import java.io.File;

import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		 features = {"src/main/java/Features"},
	        glue = {"stepDefinitions"},
	        tags = {"@Smoke"},
				plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html",
				        "pretty",
				        "html:target/cucumber-reports/cucumber-pretty",
				        "json:target/cucumber.json",
				        "json:target/cucumber-reports/CucumberTestReport.json",
				        "rerun:target/cucumber-reports/rerun.txt",
				        "junit:target/junit/cucumber.xml",
				        "json:target/cucumber-jvm-report-html/cucumber-html-reports/cucumber.json"},monochrome = true)

public class TestRunner {
	@AfterClass
    public static void writeExtentReport() {
       Reporter.loadXMLConfig(new File("config/report.xml"));
       Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
       Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
       Reporter.setSystemInfo("Machine", "Windows 10" + "64 Bit");
       Reporter.setSystemInfo("Selenium", "3.7.0");
       Reporter.setSystemInfo("Maven", "3.5.2");
       Reporter.setSystemInfo("Java Version", "1.8.0_151");
       
    }
	
	
}
