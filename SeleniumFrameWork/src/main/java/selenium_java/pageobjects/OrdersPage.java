package selenium_java.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {  
	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//tr[@class='ng-star-inserted'][1]//td[2]")
	WebElement recentOrder;
	
	public String checkOrderHistory() {
		return recentOrder.getText();
	}
	
}
