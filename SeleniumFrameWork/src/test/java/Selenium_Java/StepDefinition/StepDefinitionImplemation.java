package Selenium_Java.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Selenium_Java.GlobalComponents.CommonDriverAndData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import selenium_java.pageobjects.CartSection;
import selenium_java.pageobjects.FinalPage;
import selenium_java.pageobjects.LandingPage;
import selenium_java.pageobjects.PaymentPage;
import selenium_java.pageobjects.ProductCatalogue;

public class StepDefinitionImplemation extends CommonDriverAndData{
	public LandingPage login;
	public ProductCatalogue plist; 
//	String products[] = { "IPHONE 13 PRO", "ADIDAS ORIGINAL" };
	
	//It is advised to have methodname in stepdefintion class similar to given
	
	@Given("I landed on e-commerce page")
	public void landed_on_ecommerce_page() throws IOException{
		login = loginApp();
	}
	
//	 Logged in with user name johnqwerty@gmail.com and password  12345678A!1q 
	//The above line is matched with given below at run time. Cucumber will work only the given field is matched.
	//The (.+) is dynamic way for catching any <name> from .feature file 
	//Now the given which does not have any data coming is called static and below is example for regular expression
	//the ^ and $ will differntiate the regular expression from static expression
	
	@Given("^Logged in with user name (.+) and password (.+)$")
	public void logged_in_with_userName_and_password(String username, String password) throws InterruptedException {
		plist = login.loginApplication(username, password);
	}
	
	 @When("^I add product (.+) to cart$")
	 public void add_product_to_cart(String pName) throws InterruptedException {
		 List<WebElement> elements = plist.getProducts();
		plist.addProductToCart(elements, pName);
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void checkout_product_submit_order(String pName) throws InterruptedException {
		 CartSection cart = plist.clickOnCart();
		boolean orderPresence = cart.checkForOrderOnCart(pName);
		Assert.assertEquals(true, orderPresence);  
		PaymentPage billing = cart.proceedCheckOut();
		billing.addPaymentDetails();
	 }
	 
	 @Then("{string} message is displayed in confrimation page")
	 public void confirmation_msg_check(String string) {
		 String confirmationMsg = FinalPage.getOrderConfirmation(driver);
		 Assert.assertEquals(confirmationMsg, string); 
	 }
}



 


