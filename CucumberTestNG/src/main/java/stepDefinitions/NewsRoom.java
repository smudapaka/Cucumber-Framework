package stepDefinitions;

import java.text.DecimalFormat;
import java.util.Calendar;
//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import reusableActions.ConfigReader;
import reusableActions.ExcelReader;
import reusableActions.e2e_Scenario;


public class NewsRoom {
				 WebDriver driver;
	 
				   DecimalFormat df = new DecimalFormat("#.##");
				   ConfigReader con=new ConfigReader();
	 
			// ################### Start Test Data ##############################################
				
						ExcelReader TD = new ExcelReader("Driver.xlsx");
				        String UserName = TD.getCellData("TestData", "UserName",2).trim();
						String Passwrd = TD.getCellData("TestData", "Password",2).trim();
						String URL=con.getsynovusEnvironment();
						String path=con.getChromeDriverPath();
						
					// ###################### End Test Data ####################################
					
					
					
			@Given("^Navigate to synovus$")
			public void navigatetosynovus() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.navigateToUrl(URL,path);
				} 
			
			@Given("^Click on about us menu option$")
			public void clickonaboutusmenuoption() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.SelectaboutusMenu();
			}
			
			@When("^Click on news room option from secondary menu$")
			public void clickonNewsroommenu() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.SelectNewsroomMenu();
			}
			
			@Then("^verify news room option displayed$")
			public void verifynewsroomoptiondisplayed() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.VerifyhomePage();
				 Calendar now = Calendar.getInstance();
				    System.out.println("Current Year is : " + now.get(Calendar.YEAR));
				
			}




}
