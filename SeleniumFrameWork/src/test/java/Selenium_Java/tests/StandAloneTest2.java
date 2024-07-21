package Selenium_Java.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Selenium_Java.GlobalComponents.CommonDriverAndData;
import selenium_java.pageobjects.CartSection;
import selenium_java.pageobjects.FinalPage;
import selenium_java.pageobjects.OrdersPage;
import selenium_java.pageobjects.PaymentPage;
import selenium_java.pageobjects.ProductCatalogue;


public class StandAloneTest2 extends CommonDriverAndData{  
	
	@Test(dataProvider= "getData", groups="purchase") 
	public void submitOrder(HashMap<String,String> data) throws InterruptedException, IOException {
		System.out.println(data.get("index"));
//		String products[] = { "IPHONE 13 PRO", "ADIDAS ORIGINAL" }; 
		String products = "IPHONE 13 PRO";
		ProductCatalogue plist = login.loginApplication(data.get("email"), data.get("password")); 
		List<WebElement> elements = plist.getProducts();
		plist.addProductToCart(elements, products);
		CartSection cart = plist.clickOnCart();
		boolean orderPresence = cart.checkForOrderOnCart(products);
		Assert.assertEquals(true, orderPresence);  
		PaymentPage billing = cart.proceedCheckOut();
		billing.addPaymentDetails(); 
		String confirmationMsg = FinalPage.getOrderConfirmation(driver);
		Assert.assertEquals(confirmationMsg, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods= {"submitOrder"}) 
	public void orderHistoryTest() throws InterruptedException, IOException { 	
		ProductCatalogue plist =  login.loginApplication("johnqwerty@gmail.com", "12345678A!1q");
		OrdersPage orders = plist.clickOnOrders();
		String orderName = orders.checkOrderHistory();
		System.out.println(orderName);
	}
		
	@DataProvider
	public Object[][] getData() throws IOException{
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		HashMap<String,String> map2 = new HashMap<String,String>();
//		map1.put("email", "johnqwerty@gmail.com");
//		map1.put("password", "12345678A!1q");
//		
//		map2.put("email", "johnqwer@gmail.com");
//		map2.put("password", "12345678A!1q"); 
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//Selenium_Java//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
	}
	
	//in return of getData we create new 2D array of String type.
	//If we pass map into that array and give String as return type it will throw an error
	//So convert it to Object which is Parent for all.

}
