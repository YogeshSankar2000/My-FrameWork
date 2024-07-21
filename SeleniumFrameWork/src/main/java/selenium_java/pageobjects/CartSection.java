package selenium_java.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium_java.abstract_components.AbstractComponents;

public class CartSection extends AbstractComponents {

	WebDriver driver;

	public CartSection(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cartSection']")
	WebElement element;

	By checkout = By.xpath("//li[@class='totalRow']//button");

	public boolean checkForOrderOnCart(String products) throws InterruptedException {
		boolean flag = false;

		if (element.findElement(By.cssSelector("h3")).getText().equals(products)) {
			flag = true;
			System.out.println(element.findElement(By.cssSelector("h3")).getText());
		}
		JavascriptExecutor jr = (JavascriptExecutor) driver;
		jr.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(1000);
		return flag;
	}

	public PaymentPage proceedCheckOut() {
		waitForElmtToAppear(checkout);
		driver.findElement(By.cssSelector("button[type='button']:nth-child(1)")).click();
		return new PaymentPage(driver);
	}
}
