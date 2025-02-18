package com.AttendanceSeliniumTest.main.AttendanceModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners  implements ITestListener {
	
	WebDriver driver = new ChromeDriver();

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("Testcases started");
		
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("Testcases executed");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("Success");
		
			}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("Failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("Skipped");
		
	}


	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		
		
	}


}
