package selenium_java.pageobjects;

//import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage{ 
	
	//Defining constructor for passing driver to variable in this class
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//Calling initelements to intialise driver to all FindBy annotations 
	}
	@FindBy(id="userEmail")
	WebElement email;
	//Note import for findby will only appear after crearing webelementref for that annotations
	//the methods in WebElement can only be accessed only within any method or constructor
	
	@FindBy(id="userPassword")
	WebElement pass;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;
	
	public ProductCatalogue loginApplication(String user_email, String password) throws InterruptedException {
		email.sendKeys(user_email);
		pass.sendKeys(password);
		submit.click();
		ProductCatalogue plist = new ProductCatalogue(driver);
//		Thread.sleep(1000);
		return plist;
	}
	public String getErrorMsg() { 
		
		return errorMsg.getText();
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		//here we are forming a global method for all test case thereby in future we can tweek this and all test will use this
	}
}
//johnqwerty@gmail.com
//12345678A!1q