package com.AttendanceSeliniumTest.main;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AttendanceMarkPage {
	
WebDriver driver = new ChromeDriver();

    
@BeforeSuite
 public void LaunchBrowser(ITestContext context) throws InterruptedException {
	System.out.println("Before test");
	driver = new ChromeDriver();
	driver.get("http://localhost:4200/");
	driver.manage().window().maximize();
	Thread.sleep(4000);
}


@Test(priority = 1)
public void SubmitButtonCheck_Mark() throws InterruptedException,NoSuchElementException {
	System.out.println("test 1");
	
	  if(
		driver.findElement(By.id("submit")).isDisplayed()) {
			
		}
		else {
			throw new NoSuchElementException("Attendance mark button is not displayed.");
		}
			
	
	Thread.sleep(2000);		
	driver.findElement(By.id("submit")).click();
	
 
}


@AfterSuite
public void CloseBrowser(ITestContext context) throws InterruptedException {
	
	Thread.sleep(2000);	
	System.out.println("After test");
	
	driver.close();
}


}
