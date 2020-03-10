package reusableActions;

import org.openqa.selenium.By;

public class objectRepositary {
	
	
	//Login Page
	public  By loginusername = By.id("challengeUsername");
	public  By loginPassword = By.name("password");
	public  By LoginBtn = By.xpath("//*[@id='challenge-form']/div[3]/button[1]");
	
	
	
	//Home Page
	public  By Personal = By.xpath("//a[contains(text(),'Personal') and @class='dropdown-toggle']");
	
	public  By Charge = By.xpath("//a[text()='Charge']");
	

	public  By PromotionalOffer = By.xpath("//h5[@class='full-length']");
	
	public  By ChangePasswordlink = By.xpath("//*[@id='navigation']/ul/li[2]/a");
	
	public  By enrbillpay = By.xpath("//*[@id='main']/div/div[2]/div[2]/a/div");
	
	public  By selectdrpdwn = By.xpath("//*[@id='preferredEndpointId']/div/div[1]");
	
	public  By savebtn = By.xpath("//*[@id='main']/div/div[2]/div/div/form/div[3]/div/button");
	
	
	public  By aboutus = By.xpath("//*[@id='siteNavbar']/li[6]/a");
	public  By newsroom = By.xpath("//*[@id='siteNavbar']/li[6]/ul/li[5]/a");
	public  By Pageheader = By.xpath("//*[@id='PageHeaderContentText']");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
