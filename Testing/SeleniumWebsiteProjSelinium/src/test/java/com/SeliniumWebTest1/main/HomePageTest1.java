package com.SeliniumWebTest1.main;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners(com.SeliniumWebTest1.main.MyListeners.class)
public class HomePageTest1 {
	
WebDriver driver = new ChromeDriver();
	

	@BeforeSuite
    public void launchApp(ITestContext context) {
     
        driver.get("https://www.selenium.dev/");
        driver.manage().window().maximize();
        context.setAttribute("WebDriver", driver);
        }
        
 
//	@BeforeSuite
//	public void LaunchBrowser(ITestContext context) {
//		System.out.println("Before test");
//		driver = new ChromeDriver();
//		driver.get("https://www.selenium.dev/");
//		driver.manage().window().maximize();
//	}
//	
	@Test(priority = 1)
	public void NavigateToDownloads() throws InterruptedException {
		System.out.println("test 1");
		Thread.sleep(2000);		
		driver.findElement(By.cssSelector("a.nav-link[href=\"/downloads\"]")).click();
	}
	
	@Test(priority = 2)
	public void ClickOnBrowsers() throws InterruptedException 
	{
		System.out.println("test 2");
		System.out.println(driver.getCurrentUrl());
		WebElement browsersButton = driver.findElement(By.cssSelector("button[data-bs-target=\"#@supported-browsers\"]")); 
	       
	       JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript("arguments[0].scrollIntoView(true);",browsersButton);
	       
	       if(browsersButton.isDisplayed()) {
	    	   System.out.println("Browsers Button is displayed");
	    	   Thread.sleep(500);
	    	   browsersButton.click();
	       }
	       else {
	    	   System.out.println("Browsers Button is not displayed");
	       }
	}
	
	
	@Test(priority = 3)
	public void CheckforFirefoxLogos() throws InterruptedException {
		System.out.println("test 3");
		Thread.sleep(1000);
		// FIREFOX
       if(driver.findElement(By.xpath("//img[@alt=\"Firefox\"]")).isDisplayed())
    	   System.out.println("Fire is Displayed in the BrowerList");
       else
    	   System.out.println("Fire is Not Displayed in the BrowerList");
       
	}
	
	@Test(priority = 4)
	public void CheckforChromeLogos() throws InterruptedException {
		System.out.println("test 4");
		Thread.sleep(1000);
       //CHROME
       if(driver.findElement(By.xpath("//img[@alt=\"@Chrome\"]")).isDisplayed())
    	   System.out.println("Chrome is Displayed in the BrowerList");
       else
    	   System.out.println("Chrome is Not Displayed in the BrowerList");
	}
	
	@Test(priority = 5)
	public void CheckforEdgeLogos() throws InterruptedException {
		System.out.println("test 5");
		Thread.sleep(1000);
       //EDGE
       if(driver.findElement(By.xpath("//img[@alt=\"Edge\"]")).isDisplayed())
    	   System.out.println("Edge is Displayed in the BrowerList");
       else
    	   System.out.println("Edge is Not Displayed in the BrowerList");
       
	}
	

	@AfterSuite
	public void CloseBrowser(ITestContext context) throws InterruptedException {
		System.out.println("After test");
		Thread.sleep(2000);		
		driver.close();
	}
	
	

}
