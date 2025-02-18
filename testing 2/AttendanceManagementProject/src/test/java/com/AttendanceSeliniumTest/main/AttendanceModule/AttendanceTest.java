package com.AttendanceSeliniumTest.main.AttendanceModule;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

@Listeners(com.AttendanceSeliniumTest.main.AttendanceModule.MyListeners.class)
public class AttendanceTest {

	
	WebDriver driver =new ChromeDriver();
	
	private final String baseURL = "http://localhost:4200";  //Store the base URL here
    private final String expectedDashboardURL = baseURL + "/dashboard"; // Corrected expected URL

	
	@BeforeSuite
	 public void LaunchBrowser(ITestContext context) throws InterruptedException {
		System.out.println("Before test");
		driver = new ChromeDriver();
		driver.get( baseURL);
		
		
		
		// driver.get("http://localhost:4200"); 
	   // driver.manage().window().maximize();
	    
		driver.manage().window().maximize();
		context.setAttribute("WebDriver", driver);
		Thread.sleep(4000);
	}
	

@Test(priority = 1)
	 public void testAdminLoginAndNavigation() {
	        driver.get(baseURL);

	        //Login as admin
	        WebElement userID = driver.findElement(By.id("userid"));
	        userID.sendKeys("999");

	        WebElement password = driver.findElement(By.id("userpassword"));
	        password.sendKeys("Allen@123");

	        // Click the login button
	        WebElement loginButton = driver.findElement(By.className("btn"));
	        loginButton.click();

	        // Wait for redirection and verify URL
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.urlContains("/dashboard"));
	        String currentURL = driver.getCurrentUrl();
	        Assert.assertTrue(currentURL.contains("/dashboard"), "Admin Login Failed. Incorrect URL after login.  Actual URL: " + currentURL); //Assert login

	    }
	
  
 
@Test(priority = 2)
public void testMarkAttendance() throws InterruptedException {
//	testAdminLoginAndNavigation(); // Ensure we are logged in before proceeding
	//Attendance Module
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	driver.findElement(By.className("hamburger-btn")).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//summary[text()='Attendance']"))).click(); // Use explicit wait
	// Assert that the Attendance module is selected.  (Can add more specific checks if possible)
	// Example (replace with more precise checks based on your UI):
	// Assertions.assertTrue(driver.findElement(By.xpath("//summary[text()='Attendance']")).getAttribute("aria-expanded").equals("true"), "Attendance module not selected");

    driver.findElement(By.linkText("Give Attendance")).click();
    Thread.sleep(4000);
//    WebElement dateInput = driver.findElement(By.id("attendanceDate"));
//    dateInput.sendKeys("2025-02-16");
    driver.findElement(By.id("submit")).click();
    Thread.sleep(2000); // Consider replacing with an explicit wait for success message
    // Add assertions here to verify the attendance was marked (e.g., check for a success message, or query the database if possible).
    // Example:
    //   String successMessage = driver.findElement(By.cssSelector(".success-message")).getText();
    //   Assertions.assertTrue(successMessage.contains("Attendance marked successfully"), "Attendance not marked successfully");
}


@Test(priority = 3)
public void testAttendanceHistory() throws InterruptedException {
//    testAdminLoginAndNavigation();  // Ensure we are logged in before proceeding
	
	driver.findElement(By.className("hamburger-btn")).click();
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	 Thread.sleep(2000);
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//summary[text()='Attendance']"))).click(); 
    driver.findElement(By.linkText("Students")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("FilterAttendanceS")).click();
    Thread.sleep(2000);
    //driver.findElement(By.className("hamburger-btn")).click(); //Close sidebar
    //Thread.sleep(1000);
    WebElement startDate = driver.findElement(By.xpath("//input[@formcontrolname='startDate']"));
    startDate.sendKeys("01-01-2025");
    Thread.sleep(2000);
    WebElement endDate = driver.findElement(By.xpath("//input[@formcontrolname='endDate']"));
    endDate.sendKeys("31-01-2025");
    Thread.sleep(2000);
    WebElement dropdown = driver.findElement(By.id("form-select"));
    dropdown.click();
    dropdown.sendKeys("Present");
    dropdown.sendKeys(Keys.ENTER);
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);

//     Add assertions to verify the attendance history results (e.g., check the table contents).
//     Example (assuming a table with attendance records):
//       List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
//       Assertions.assertTrue(rows.size() > 0, "No attendance records found in history");
}
 
//@Test(priority = 4)
//public void testViewStudentAttendances() throws InterruptedException {
//    testAdminLoginAndNavigation(); // Ensure login first
//    driver.findElement(By.linkText("View Student Attendances")).click();
//     driver.findElement(By.className("hamburger-btn")).click();
//    WebElement startDate1 = driver.findElement(By.xpath("//input[@formcontrolname=\"startDate\"]"));
//    startDate1.sendKeys("01-01-2025");
//    WebElement endDate1 = driver.findElement(By.xpath("//input[@formcontrolname=\"endDate\"]"));
//    endDate1.sendKeys("31-01-2025");
//    WebElement id = driver.findElement(By.xpath("//input[@formcontrolname='id']"));
//    id.click();
//    id.sendKeys("100");
//    WebElement dropdown2 = driver.findElement(By.className("form-select"));
//    dropdown2.click();
//    dropdown2.sendKeys("Present");
//    dropdown2.sendKeys(Keys.ENTER);
//    driver.findElement(By.xpath("//button[@type='submit']")).click();
//    Thread.sleep(2000);
//    driver.findElement(By.className("hamburger-btn")).click();
//    Thread.sleep(1000);
//    driver.findElement(By.linkText("View Student Attendances")).click();
//    driver.findElement(By.cssSelector("table tbody tr:first-child button.btn.btn-warning")).click();
//    WebElement dropdown3 = driver.findElement(By.className("form-select"));
//    dropdown3.click();
//    dropdown3.sendKeys("Leave");
//    driver.findElement(By.className("btn-success")).click();
//    Thread.sleep(2000);
//    driver.findElement(By.cssSelector("table tbody tr:first-child button.btn.btn-danger")).click();
//    Thread.sleep(1000); // Allow time for deletion confirmation
//    // Add assertions to check that the update and delete operations were successful.
//    // Example (check for updated status in the table, check for a success message):
//    // String updatedStatus = driver.findElement(By.cssSelector("table tbody tr:first-child td:nth-child(4)")).getText();
//    // Assertions.assertEquals("Leave", updatedStatus, "Attendance status not updated.");
//}
//
//
//@Test(priority = 4)
//public void testViewTeacherAttendances() throws InterruptedException {
//   testAdminLoginAndNavigation(); //Login
//    driver.findElement(By.linkText("View Teacher Attendances")).click();
//     driver.findElement(By.className("hamburger-btn")).click();
//    WebElement startDate2 = driver.findElement(By.xpath("//input[@formcontrolname=\"startDate\"]"));
//    startDate2.sendKeys("01-01-2025");
//    WebElement endDate2 = driver.findElement(By.xpath("//input[@formcontrolname=\"endDate\"]"));
//    endDate2.sendKeys("31-01-2025");
//    WebElement id1 = driver.findElement(By.xpath("//input[@formcontrolname='id']"));
//    id1.click();
//    id1.sendKeys("500");
//    WebElement dropdown4 = driver.findElement(By.className("form-select"));
//    dropdown4.click();
//    dropdown4.sendKeys("Leave");
//    dropdown4.sendKeys(Keys.ENTER);
//    driver.findElement(By.xpath("//button[@type='submit']")).click();
//    Thread.sleep(2000);
//    WebElement dropdown5 = driver.findElement(By.className("form-select"));
//    dropdown5.click();
//    dropdown5.sendKeys("Present");
//    dropdown5.sendKeys(Keys.ENTER);
//    driver.findElement(By.xpath("//button[@type='submit']")).click();
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//    driver.findElement(By.cssSelector("table tbody tr:first-child button.btn.btn-warning")).click();
//    WebElement dropdown6 = driver.findElement(By.className("form-select"));
//    dropdown6.click();
//    dropdown6.sendKeys("Absent");
//    driver.findElement(By.className("btn-success")).click();
//    Thread.sleep(2000);
//    driver.findElement(By.cssSelector("table tbody tr:first-child button.btn.btn-danger")).click();
//    Thread.sleep(1000);
//    // Add assertions for update and delete, similar to the student attendance test.
//}
//
//@Test(priority = 5)
//public void testViewAllAttendances() throws InterruptedException {
//    testAdminLoginAndNavigation(); // Login
//     driver.findElement(By.linkText("View All Attendances")).click();
//      driver.findElement(By.className("hamburger-btn")).click();
//     WebElement startDate3 = driver.findElement(By.xpath("//input[@formcontrolname=\"startDate\"]"));
//     startDate3.sendKeys("01-01-2025");
//     WebElement endDate3 = driver.findElement(By.xpath("//input[@formcontrolname=\"endDate\"]"));
//     endDate3.sendKeys("31-01-2025");
//     WebElement roledropdown = driver.findElement(By.xpath("//select[@formcontrolname='role']"));
//     roledropdown.click();
//     roledropdown.sendKeys("Admin");
//     WebElement dropdown7 = driver.findElement(By.className("form-select"));
//     dropdown7.click();
//     dropdown7.sendKeys("Present");
//     dropdown7.sendKeys(Keys.ENTER);
//     driver.findElement(By.xpath("//button[@type='submit']")).click();
//     Thread.sleep(2000);
//     driver.findElement(By.cssSelector("table tbody tr:first-child button.btn.btn-warning")).click();
//     WebElement dropdown8 = driver.findElement(By.className("form-select"));
//     dropdown8.click();
//     dropdown8.sendKeys("Absent");
//     driver.findElement(By.className("btn-success")).click();
//     Thread.sleep(2000);
//     driver.findElement(By.cssSelector("table tbody tr:first-child button.btn.btn-danger")).click();
//     Thread.sleep(1000); // Allow time for deletion confirmation
//     // Add assertions for update and delete, similar to the other tests.
// }


@AfterSuite
public void CloseBrowser(ITestContext context) throws InterruptedException {
	
	Thread.sleep(2000);	
	System.out.println("After test");
	
//	driver.close();
}

 	
}


