package selenium_java.pageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class FinalPage {
	public static  String getOrderConfirmation(WebDriver driver) {
		String msg = driver.findElement(By.xpath("//h1[text()=' Thankyou for the order. ']")).getText();
		return msg;
	}
	
}
