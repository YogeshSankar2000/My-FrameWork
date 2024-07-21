package selenium_java.pageobjects;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {
	
	WebDriver driver;
	public PaymentPage(WebDriver driver) {  
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.form-group input")
	WebElement cntry;
	
	@FindBy(xpath="//div[@class='form-group']//section//button//span")
	List<WebElement> elmts;
	
	@FindBy(xpath="//a[contains(@class,'action__submit')]")
	WebElement placeOrder;
	
	public void addPaymentDetails() {
		cntry.sendKeys("Ind");
		for(WebElement e:elmts) {
			if(e.getText().equals("India")) {
				e.click();
				break;
			}
		}
		placeOrder.click();
	}
}

//driver.findElement(By.cssSelector("div.form-group input")).sendKeys("Ind");
//List<WebElement> elmts = driver.findElements(By.xpath("//div[@class='form-group']//section//button//span"));
//driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();