package Selenium_Java.GlobalComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium_java.resources.ExtentReporterNG;


//Thes methods won't get added automaticlally just right clik on ITestLitener and source add override
public class Listeners extends CommonDriverAndData implements ITestListener{

	ExtentTest test; 
	ExtentReports extent = ExtentReporterNG.generateReport();  
	//Since extent obj in other package we declae globally agter calling their methods
	//We will then dynamically use extent to ceatre test in these methods
	//When test starts firstly the below method get activated so we simply create extent.createtestcase inside this
	
	ThreadLocal<ExtentTest> thread = new ThreadLocal<ExtentTest>();  //Thread Safe
	//ThreadLocal objetc make sure even if we run parallel test that each extent object is unique to a partivular tst. 
	//the set method will generate unique thred id for all extent obj
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName()); 
		//as the name says we getmethod name of test which is goinon rigth now ans assign it as TestCase Name in HTML report
		//test is obj for extentTest now the methodname.
		thread.set(test);   //unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		test.log(Status.PASS, "I executed without any error");
		thread.get().log(Status.PASS, "I executed without any error");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		thread.get().fail(result.getThrowable()); //getThrowable will give the error description
		//taking screenshot when test fail
		//We dynamacially take current driver for current test below.
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String snanpDir = null;
		try {
			snanpDir = getScreenShot(result.getMethod().getMethodName(), driver); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		thread.get().addScreenCaptureFromPath(snanpDir, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	
	}

	@Override
	public void onStart(ITestContext context) {
	
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
