package stepDefinitions;

import reusableActions.ConfigReader;
import reusableActions.ExcelReader;
import reusableActions.e2e_Scenario;

import java.text.DecimalFormat;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LogintoApplication {
	
	
	  
	   DecimalFormat df = new DecimalFormat("#.##");
	   ConfigReader con=new ConfigReader();
	   
	   
	    String URL=con.getsynovusEnvironment();
		String path=con.getChromeDriverPath();
		
		//for(int i = 0;i<=5;i++){
			
		// ################### Start Test Data ##############################################
		
			ExcelReader TD = new ExcelReader("Driver.xlsx");
	        String UserName = TD.getCellData("TestData", "UserName",2).trim();
			String Passwrd = TD.getCellData("TestData", "Password",2).trim();
			
		// ###################### End Test Data ####################################

			
				
			@Given("^Launch My Synovus")
			public void launchmysynovus() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.navigateToUrl(URL,path);
			}

			@When("^Click Personal")
			public void ClickPersonal() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.ClickPersonal();
			}
			
			@When("^Click Charge")
			public void ClickCharge() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.ClickCharge();
			}

			@Then("^Verify Promotional Offer")
			public void VerifyPromotionalOffer() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.VerifyPromotionalOffer();
				//obj.EnrollBillPay();
				//obj.selectBillPayEnrollment();
				//obj.selectSave();
			}
			
			@Then("^verify home Page$")
			public void verifyhome_Page() throws Throwable {
				e2e_Scenario obj=new e2e_Scenario();
				obj.ClickPymentslink();
				obj.EnrollBillPay();
				obj.selectBillPayEnrollment();
				obj.selectSave();
			}

			
			// } 
		

}
