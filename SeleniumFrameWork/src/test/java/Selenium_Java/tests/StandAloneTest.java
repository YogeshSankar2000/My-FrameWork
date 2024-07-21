package Selenium_Java.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		String products[] = { "IPHONE 13 PRO", "ADIDAS ORIGINAL" };
		String product1 = "ADIDAS ORIGINAL";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("johnqwerty@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("12345678A!1q");
		driver.findElement(By.id("login")).click();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card-body']"));
//		for(WebElement element:elements) {
//			if((element.findElement(By.cssSelector("b")).getText()).equals(product)) {
//				element.findElement(By.cssSelector("button:last-of-type")).click();
//				
//			}
//		}
		for (String product : products) {
			WebElement prod = elements.stream()
					.filter(prd -> prd.findElement(By.cssSelector("b")).getText().equals(product)).findFirst()
					.orElse(null);
			prod.findElement(By.cssSelector("button:last-of-type")).click();
			// findFirst will get the first filtered result irrespective of how many are
			// resulted by filtering and or eslse will give null if
			// nothing has been filtered

			// driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
			// above code will throe error

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			// ng-animating
			// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#ng-animating")));
			// above line will do same job as below but it takes some time hence below line
			// is recommended you will find the diff
			// if code run with both line sperately, the locator seems differnt

			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		}
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink='/dashboard/cart']")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		List<WebElement> elements1 = driver.findElements(By.xpath("//div[@class='cartSection']"));
//		for(String product:products) {
//			boolean flag  = elements1.stream().anyMatch(cart->cart.findElement(By.cssSelector("h3")).getText().equals(product1));
//			System.out.println(flag);
//			elements1.

//			Assert.assertEquals(true, flag);
//		}
		boolean flag = false;

		for (String product : products) {
			for (WebElement element : elements1) {
				if (element.findElement(By.cssSelector("h3")).getText().equals(product)) {
					flag = true;
					System.out.println(flag);
					System.out.println(element.findElement(By.cssSelector("h3")).getText());
				}
			}
			Assert.assertEquals(true, flag);
		}
		JavascriptExecutor jr = (JavascriptExecutor) driver;
		jr.executeScript("window.scrollBy(0,1000)");
//		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='totalRow']//button")));
		driver.findElement(By.cssSelector("button[type='button']:nth-child(1)")).click();
		driver.findElement(By.cssSelector("div.form-group input")).sendKeys("Ind");
		List<WebElement> elmts = driver.findElements(By.xpath("//div[@class='form-group']//section//button//span"));
//		elmts.stream().filter(cntry->cntry.getText().equals("India")).forEach(cntry->cntry.click());
		for(WebElement e:elmts) {
			if(e.getText().equals("India")) {
				e.click();
				break;
			}
		}
		driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
		List<WebElement> e = driver.findElements(By.cssSelector("label[class='ng-star-inserted']"));
		List<String> orderId = e.stream().map(a->a.getText()).collect(Collectors.toList());
		for(String id:orderId) {
			System.out.println(id);
		}
	}
}
//doubt on prod webelement line and elements1 list line(basically when to use xpath and css)


























