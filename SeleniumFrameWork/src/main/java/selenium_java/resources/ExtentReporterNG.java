package selenium_java.resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent; //making extent avialable global to use on other methd outside of generateReport
	public static ExtentReports generateReport() { 
		String destfile = System.getProperty("user.dir")+"\\Report\\index.html";
		ExtentSparkReporter report = new ExtentSparkReporter(destfile);
		report.config().setDocumentTitle("Testing");
		report.config().setReportName("Web Automation Testing");
		//It is child class to extentreport and it is helper class which do some basic opertion
		
		extent = new ExtentReports();
		extent.attachReporter(report);  
		extent.setSystemInfo("Tester", "Yogesh Sankar");
		//so extent is obj which help to cratetest for a method specific to that
		return extent;  
	}

	public void sampleMethod() {
		ExtentTest sampleTest = extent.createTest("Test Demo"); 
		//catching obj created for test which inturn resposible for reporting changes to report
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();
		sampleTest.fail("I explicitly fialed"); //here we ouself make test to fail to see the result being pushed to HTML filr
		extent.flush();  //This line is mandatory else it keeps being inlistening mode
		
	} 
}
