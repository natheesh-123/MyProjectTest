package com.SeliniumWebTest1.main.RolesModule;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.SeliniumWebTest1.main.AttendanceTest;

//import org.openqa.selenium.support.ui.WebDriverWait;

/*
 *
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
 */

public class RoleTest extends AttendanceTest {
	
	AttendanceTest AttnTest=new AttendanceTest();
	
	WebDriver driver =new ChromeDriver();

//	  @BeforeSuite
//	    public void setup(ITestContext context) throws InterruptedException {
//	        System.out.println("Launching browser and opening application");
//	       // driver = new ChromeDriver();
//	        //driver.get("http://localhost:4200/dashboard/editroles")
	

//	        
//	        driver.get("http://localhost:4200");
//	        driver.manage().window().maximize();
//	       // context.setAttribute("WebDriver", driver);
//	        Thread.sleep(4000);
//	    }
//
//	    @Test(priority = 1)
//	    public void loginTest() throws InterruptedException {
//	    	String expectedURL = "http://localhost:4200/dashboard";
//	        System.out.println("Testing login functionality");
//	        Thread.sleep(2000);
//	        WebElement userIdInput = driver.findElement(By.id("userid"));
//	        Thread.sleep(2000);
//	        
//	        WebElement passwordInput = driver.findElement(By.id("userpassword"));
//	        Thread.sleep(2000);
//	        
//	        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
//	        
//	        userIdInput.sendKeys("999");
//	        Thread.sleep(2000);
//	        passwordInput.sendKeys("Allen@123");
//	        Thread.sleep(2000);
//	        loginButton.click();
//	        
//	        String currentURL = driver.getCurrentUrl();
//
//	        if (currentURL.equals(expectedURL)) {
//	            System.out.println("Login Successful: logged as student Redirected to Dashboard");
//	        } else {
//	            System.out.println("Login Failed or Incorrect Redirection");
//	        }    
             
//	    }
	    
	    
	    

	    @Test(priority = 4)
	    public void checkAddRoleButton() throws InterruptedException {
	    	
	    	driver.get(AttnTest.expectedDashboardURL);

			driver.manage().window().maximize();
	    	
	    	 //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    		 
	  	    // wait.until(ExpectedConditions.elementToBeClickable(By.id("RoleCheckAddRole"))).click();
	  	     

//	    	driver.get("http://localhost:4200/dashboard");
	    	//String expectedURL = "http://localhost:4200/dashboard/editroles";
	    	//String currentURL = driver.getCurrentUrl();
//
//	        if (currentURL.equals(expectedURL)) {
//	            System.out.println("student Redirected to editroles");
//	        } else {
//	            System.out.println(" Incorrect Redirection to editroles");
//	        }
	    	Thread.sleep(2000);
	    	
	    	driver.findElement(By.className("hamburger-btn")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.linkText("Roles")).click();

	    	Thread.sleep(2000);
	    	driver.findElement(By.linkText("View Roles")).click();
	    	Thread.sleep(2000);
	    	
	    	
	    	driver.findElement(By.className("hamburger-btn")).click();
	    	Thread.sleep(2000);
	    	driver.findElement(By.linkText("Roles")).click();
	    	Thread.sleep(4000);
	    	driver.findElement(By.linkText("Edit Roles")).click();

	    	
	    	System.out.println("Checking Add Role button");
	        Thread.sleep(2000);
	        driver.findElement(By.id("RoleCheckAddRole")).click();
	        //addRoleButton.click();
	        Thread.sleep(2000);
	        System.out.println("Adding a new role");
	        
	        WebElement roleNameInput = driver.findElement(By.cssSelector("input[formControlName='RoleroleName']"));
	        Thread.sleep(4000);
	        roleNameInput.sendKeys("Stud");
	        Thread.sleep(4000);
	        driver.findElement(By.id("RoleCheckAdd")).click();
	       
	    }
//
//	    @Test(priority = 3)
//	    public void addNewRole() throws InterruptedException {
//	        System.out.println("Adding a new role");
//	        Thread.sleep(2000);
//	        WebElement roleNameInput = driver.findElement(By.cssSelector("input[formControlName='RoleroleName']"));
//	        
//	        
//	        roleNameInput.sendKeys("Stud");
//	        driver.findElement(By.id("RoleCheckAdd")).click();
//	       // addButton
//	    }
//
//	    @Test(priority = 3)
//	    public void editRole() throws InterruptedException {
//	        System.out.println("Editing a role");
//	        Thread.sleep(2000);
//	        WebElement editButton = driver.findElement(By.id("Check_Edit"));
//	        editButton.click();
//	        
//	        WebElement roleNameInput = driver.findElement(By.cssSelector("input[formControlName='RoleroleName']"));
//	        roleNameInput.clear();
//	        roleNameInput.sendKeys("Updated Test Role");
//	        
//	        WebElement updateButton = driver.findElement(By.id("CheckUpdate"));
//	        updateButton.click();
//	    }
//
//	    @Test(priority = 4)
//	    public void deleteRole() throws InterruptedException {
//	        System.out.println("Deleting a role");
//	        Thread.sleep(2000);
//	        WebElement deleteButton = driver.findElement(By.id("CheckDelete"));
//	        deleteButton.click();
//	    }
//
//	    @AfterSuite
//	    public void tearDown() throws InterruptedException {
//	        System.out.println("Closing browser");
//	        Thread.sleep(2000);
//	      
//	    }
	

}
