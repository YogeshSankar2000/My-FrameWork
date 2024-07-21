package Selenium_Java.GlobalComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFlakeTest implements IRetryAnalyzer{

	int c = 0;
	int m = 1;
	@Override
	public boolean retry(ITestResult result) {
		if(c<m) {
			c++;
			return true; 
		} 
		return false;
	}
		
	
}
