package Selenium_Java.GlobalComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import selenium_java.pageobjects.LandingPage;

public class CommonDriverAndData {   
	public WebDriver driver;  
	public LandingPage login; 
	public WebDriver intializeDriver() throws IOException {
		// The step for intializing driver is same for across browser so we create a
		// seperate class to invoke driver based upon what we give in properties file
		Properties prp = new Properties();
		FileInputStream fStream = new FileInputStream("D:\\Eclipse_workspace\\SeleniumFrameWork\\src\\main\\java\\selenium_java\\resources\\GlobalData.properties");
		prp.load(fStream);
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prp.getProperty("global");
		if(browser.contains("chrome")) { 
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless")) {
				options.addArguments("headless");    //make the broweser to run in headless mode anf if headless.
//				options.addArguments("screenshot");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //Will avoid error whils elecment locationa and interaction and run on full window
		}
		else if(browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver; 
	}
	public List<HashMap<String, String>> getJsonData(String filepath) throws IOException {
		Charset charsetName = StandardCharsets.UTF_8;
		
		//The Below line will read Json file and convert it to string(In bussiness we would get data in from of json)
		//Charset is standard argument need to be passed
		String jsonContent = FileUtils.readFileToString(new File(filepath), charsetName);
		
		//Converting the String to HashMap which then can be parsed to dataprovider
		//For this operatioin jackson builder is used add dependies 
		//ObjectMapper is a class in that that would help us
		
		ObjectMapper obj = new ObjectMapper();
		
		List<HashMap<String,String>> data = obj.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {});
		
		//{map, map} is the format of data which is list so we are converting Jsoncontent to an List of hashMap using tyeprefernce and readvalue method
		
		return data; 
	}
	
	@BeforeMethod(alwaysRun=true) 
	public LandingPage loginApp() throws IOException {
		driver = intializeDriver(); 
		login = new LandingPage(driver);
		login.goTo(); 
		return login; 
	} 
	
	@AfterMethod(alwaysRun=true) 
	public void endOftest() throws InterruptedException {
		driver.quit();
//		Thread.sleep(2000);
	}
	  
	public String getScreenShot(String testCase, WebDriver driver) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File source = screen.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"//Reports//"+testCase+".png");
		FileUtils.copyFile(source, target);
		return System.getProperty("user.dir")+"//Reports//"+testCase+".png";
	}
}
