package selenium_java.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy; 
import org.openqa.selenium.support.PageFactory;


import selenium_java.abstract_components.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card-body']"));
	@FindBy(xpath="//div[@class='card-body']")
	List<WebElement> elements_;
	
	public List<WebElement> getProducts() {
		return elements_;
	}
	By toast = By.cssSelector("#toast-container");
	
	By cartButton = By.cssSelector("button[routerlink='/dashboard/cart']");
	
	@FindBy(css=".ng-animating")
	WebElement animation;
	
	public void addProductToCart(List<WebElement> elmts, String pName) throws InterruptedException {
			WebElement prod = elmts.stream()
					.filter(prd -> prd.findElement(By.cssSelector("b")).getText().equals(pName)).findFirst()
					.orElse(null);
			prod.findElement(By.cssSelector("button:last-of-type")).click();
			// findFirst will get the first filtered result irrespective of how many are
			// resulted by filtering and or eslse will give null if
			// nothing has been filtered

			// driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
			// above code will throe error

//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			waitForElmtToAppear(toast);
			// ng-animating
			// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#ng-animating")));
			// above line will do same job as below but it takes some time hence below line
			// is recommended you will find the diff
			// if code run with both line sperately, the locator seems differnt

//			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			waitForElmtToDisappear(animation);
		Thread.sleep(1000);
		waitForElmtToAppear(cartButton);
	}
}
