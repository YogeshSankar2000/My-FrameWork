package Selenium_Java.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Selenium_Java.GlobalComponents.CommonDriverAndData;
import Selenium_Java.GlobalComponents.RetryFlakeTest;
import selenium_java.abstract_components.AbstractComponents;
import selenium_java.pageobjects.CartSection;
import selenium_java.pageobjects.OrdersPage;
import selenium_java.pageobjects.ProductCatalogue;

public class HandlingErrorTest extends CommonDriverAndData{ 
	
	@Test(groups= {"errors"}, retryAnalyzer=RetryFlakeTest.class)   
	public void submitOrderError() throws InterruptedException, IOException {
		login.loginApplication("johnqwerty@gmai.com", "12345678A!1q");
		Thread.sleep(1000);
		Assert.assertEquals(login.getErrorMsg(), "Incorrect email or password.");
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
//		String productName[] = { "ZARA COAT 3", "ZARA COAT 3"};
		String products = "ZARA COAT 3";
		ProductCatalogue plist = login.loginApplication("johnqwerty@gmail.com", "12345678A!1q");
		List<WebElement> product = plist.getProducts();
		plist.addProductToCart(product, products);  
		CartSection cartPage = plist.clickOnCart();
		Boolean match = cartPage.checkForOrderOnCart(products);
		Assert.assertTrue(match);
	}
}
	
