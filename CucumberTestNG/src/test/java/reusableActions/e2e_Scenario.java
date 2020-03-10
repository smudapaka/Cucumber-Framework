package reusableActions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.cucumber.listener.Reporter;

public class e2e_Scenario extends objectRepositary {

	//public static AppiumDriver appiumDriver = null;
	public static WebDriver driver = null;
    objectRepositary ob=new objectRepositary();
	commanFunctions cm = new commanFunctions();

	String temp = cm.randomStringGenerator(3);
	String Phone = cm.numGeneration();

	
	// ########################  Business Functions ################################
	
		
			public synchronized void navigateToUrl(String url,String Path) {
				try {
				System.setProperty("webdriver.chrome.driver",Path);
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.get(url);
				Reporter.addStepLog("Logged in to My Synovus application");
				} catch (Exception e) {
					Reporter.addStepLog("Unable to My Synovus application");
				} 
			}
			
			public synchronized void ClickPersonal()throws Throwable {
				try {
				Thread.sleep(2000);
				//type(ob.loginusername,UserName, "Entered User Name");
				//type(ob.loginPassword, Password, "Enter password");
				//click(ob.LoginBtn, "Click on Login Button");
				driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
				driver.findElement(Personal).click();
				Reporter.addStepLog("Click on Personal");
				} catch (Exception e) {
					Reporter.addStepLog("Unable to click on Personal");
					driver.close();
				} 
			}
			
			public synchronized void ClickCharge()throws Throwable {
				try {
				Thread.sleep(2000);
				//type(ob.loginusername,UserName, "Entered User Name");
				//type(ob.loginPassword, Password, "Enter password");
				//click(ob.LoginBtn, "Click on Login Button");
				driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
				driver.findElement(Charge).click();
				Reporter.addStepLog("Click on Charge");
				} catch (Exception e) {
					Reporter.addStepLog("Unable to click on Charge");
					driver.close();
				} 
			}
			
			public synchronized void VerifyPromotionalOffer() throws InterruptedException {
				try {
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					boolean bvalue = driver.findElement(PromotionalOffer).isDisplayed();
					if(bvalue==true){
						Reporter.addStepLog("Promotional Offer displayed");
					}else{
						Reporter.addStepLog("PromotionalOffer is not displayed");
					}
					} catch (Exception e) {
						e.printStackTrace();
					}
					Thread.sleep(5000);
				}
			
				
			public synchronized void ClickPymentslink() throws InterruptedException {
				try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(Personal).click();
				Reporter.addStepLog("Time Sheet home page displayed");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(5000);
			}

			
			public synchronized void EnrollBillPay() throws InterruptedException {
				try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.findElement(enrbillpay).click();
				Reporter.addStepLog("Time Sheet home page displayed");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(5000);
			}
			
			public synchronized void selectBillPayEnrollment() throws InterruptedException {
				try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				Select dropdown = new Select(driver.findElement(selectdrpdwn));
				dropdown.selectByIndex(0);
				Reporter.addStepLog("Time Sheet home page displayed");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(5000);
			}

			public synchronized void selectSave() throws InterruptedException {
				try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.findElement(savebtn).click();
				Reporter.addStepLog("Time Sheet home page displayed");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(5000);
			}
			
	
			public synchronized void SelectaboutusMenu() throws InterruptedException {
				try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.findElement(aboutus).click();
				Reporter.addStepLog("About Us Clicked Successfully");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(5000);
			}
			
			public synchronized void SelectNewsroomMenu() throws InterruptedException {
				try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.findElement(newsroom).click();
				Reporter.addStepLog("Newsroom Clicked Successfully");
				} catch (Exception e) {
					e.printStackTrace();
				}
				Thread.sleep(5000);
			}
			
			public synchronized void VerifyhomePage() throws InterruptedException {
				try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(5000);
				WebElement homePage=driver.findElement(newsroom);
				String homePagetext=homePage.getText();
				System.out.println("Page titele is "  +homePagetext);
				if(homePagetext.contains("Newsroom")){
					Reporter.addStepLog("Home Page is displyed Successfully");
					}else{
						Reporter.addStepLog("Home Page is not displyed");
					}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				driver.close();
				Thread.sleep(5000);
			}
			
			
			
			
			
			
	
	//############################## Common Functions ######################################################
	
			
		public boolean click(By locator, String locatorName) throws Throwable {
				
				boolean flag = false;
				try {
					driver.findElement(locator).click();
					//logger.pass("Successfully clicked on " + locatorName);
					Reporter.addStepLog("SucessFully Click on "+locatorName);
					//successReport("Successfully clicked on " + locatorName);
					flag = true;
				} catch (Exception e) {
					e.printStackTrace();
					//logger.fail("Unable to clicked on " + locatorName);
					
					//failureReport(locatorName, "Unable to clicked on " + locatorName);
				} 
				return flag;
			}
		
		public boolean type(By locator, String testdata, String locatorName) throws Throwable {
			boolean flag = false;
			try {
				driver.findElement(locator).clear();
				/*Robot r = new Robot();
				r.keyPress(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(200);
				r.keyRelease(KeyEvent.VK_BACK_SPACE);
				Thread.sleep(500);*/
				//Thread.sleep(1000);
				driver.findElement(locator).sendKeys(testdata);
				flag = true;
				Reporter.addStepLog("Entered details " +testdata);
				Thread.sleep(100);
				//logger.pass("Data typing action is performed on " + locatorName + " with data " + testdata.replace(":", ""));
			} catch (Exception e) {
				e.printStackTrace();
				//logger.fail("Data typing action is not perform on " + locatorName);
				//failureReport(locatorName, "Data typing action is performed on " + locatorName + " with data " + testdata.replace(":", ""));
			}
			return flag;
		}
		


}