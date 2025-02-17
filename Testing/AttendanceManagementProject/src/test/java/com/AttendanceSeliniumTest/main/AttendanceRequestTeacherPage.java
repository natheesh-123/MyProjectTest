package com.AttendanceSeliniumTest.main;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AttendanceRequestTeacherPage {

WebDriver driver = new ChromeDriver();

@BeforeSuite
 public void LaunchBrowser(ITestContext context) throws InterruptedException {
	System.out.println("Before test");
	driver = new ChromeDriver();
	driver.get("http://localhost:4200/");
	driver.manage().window().maximize();
	Thread.sleep(5000);
}


@Test(priority = 1)
public void ApproveButtonCheck() throws InterruptedException,NoSuchElementException {
	System.out.println("test 1");
			
		
	if(
	driver.findElement(By.id("Approve5002023-01-03")).isDisplayed()) {
		
	}
	else {
		throw new NoSuchElementException("Attendance mark button is not displayed.");
		
		
	}
	
	Thread.sleep(2000);
	
	driver.findElement(By.id("Approve5002023-01-03")).click();

	Thread.sleep(4000);
 
}


@Test(priority = 2)
public void rejectButtonCheck() throws InterruptedException,NoSuchElementException {
	System.out.println("test 1");
	
	if(
			driver.findElement(By.id("Reject5002023-01-04")).isDisplayed()) {
				
			}
			else {
				throw new NoSuchElementException("Attendance mark button is not displayed.");
			}
			
	
	Thread.sleep(2000);		
	driver.findElement(By.id("Reject5002023-01-04")).click();

	
	Thread.sleep(4000);
	
 
}


@AfterSuite
public void CloseBrowser(ITestContext context) throws InterruptedException {
	Thread.sleep(2000);	
	System.out.println("After test");
		
	driver.close();
}




}
