package selenium_java.abstract_components;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

import selenium_java.pageobjects.CartSection;
import selenium_java.pageobjects.OrdersPage;

public class AbstractComponents {
	WebDriver driver; 

	public AbstractComponents(WebDriver driver) { 
		this.driver=driver; 
		//Resson for this.driver not using is that to avoid creating object for this class in orderHistoryTest()
		//StandAloneTest don't extend this class and by making clickorders ststic we have forced to convert WebDriver to static
		//Because of non-static reference 
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(xpath="(//button//i)[2]")
	static 
	WebElement ordersButton;
	
	public void waitForElmtToDisappear(WebElement location) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(location));
	} 
	
	public void waitForElmtToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public CartSection clickOnCart() {
		cartButton.click();
		return new CartSection(driver);
	}

	public OrdersPage clickOnOrders() {
		ordersButton.click();
		return new OrdersPage(driver);
	}
}
