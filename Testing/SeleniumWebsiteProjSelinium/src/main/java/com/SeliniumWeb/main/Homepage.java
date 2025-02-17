package com.SeliniumWeb.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class Homepage 
{
    public static void main( String[] args ) throws InterruptedException
    {
       
    	
    	WebDriver driver = new ChromeDriver();
    	
    	
    	driver.get("https://www.selenium.dev/");
    	
    	Thread.sleep(3000);
    	
    
    	//downloads
       driver.findElement(By.xpath("//a[@class='nav-link']")).click();
       
       Thread.sleep(3000);
       
    // used ex: driver.findElement(By.xpath("//a[@class=\"nav-link\"]")).click();
       
       driver.findElement(By.xpath("//a[@href=\"/projects\"]")).click();
       
       Thread.sleep(3000);
       driver.findElement(By.xpath("//a[@href=\"/documentation\"]")).click();

       Thread.sleep(3000);
       
       //driver.findElement(By.xpath("//a[@class=\"nav-link\"]")).click();
       
       driver.findElement(By.id("navbarDropdown")).click();
       
       
    	
    	
    	
    	/*
    	
    	Different Types of Locators in Selenium WebDriver
       
    	
    	1.id locator
    	
    	  syntax : driver.findElement(By.id(“IdValue”));
    	  /// ex: driver.findElement(By.id("input-email"))
    	  ///-- no DOM element matches the required id, NoSuchElementException is thrown. 
    	
    	2.name locator
    	
    	  syntax : driver.findElement(By.name(“nameValue”));
    	  /// ex: driver.findElement(By.name("email"));
    	  /// -- If there are WebElements with the same name, the locator selects the first element with that Name on the page.
    	        In case no such name matches with the defined attribute value, NoSuchElementException is raised.
    	
    	3.classnameLocator
    	
    	  syntax : driver.findElement(By.className(“classValue”));
    	  /// ex: driver.findElement(By.className("btn-dark"));
    	
    	
 
        4.LinkText Locator
        
           syntax : driver.findElement(By.linkText(“textofLink”));
          /// ex: => <a href="https://www.lambdatest.com/selenium-playground/ajax-form-submit-demo" class="text-black">Ajax Form Submit&lt;/a>
                 driver.findElement(By.linkText("Ajax Form Submit"));
                 
          /// -- which is present in the hyperlinks
             -- this identifier strategy can only be used for elements with an anchor( < a > ) tag.
       
        5. partialLinkText 
       
        6.tagName
       
          syntax : driver.findElement(By.tagName(“htmlTag”));
          ///  ex: driver.findElements(By.tagName("a"));
          /// -- The TagName locator in Selenium is commonly used to identify all the
                links on a page and identify broken links in Selenium. It is also used
                to get the header or the title of the web pages.
       
         7.cssSelector
           
           syntax : driver.findElement(By.cssSelector(“cssValue”));
           
       
             /// --CSS is also one of the widely-used ways to locate WebElements in the DOM.
                   It can be chosen over the XPath locator.(CSS Selectors since those are faster than XPath)
             
        1.Tag and ID in CSS Selector
        
         value pattern in ===> css=(Html tag )(#) (value of the ID attribute)
         
         ex: => <input type="text" name="firstname" value="" placeholder="First Name" id="input-firstname" class="form-control">
                driver.findElement(By.cssSelector("input#input-firstname"))
        
        2.Tag and Class in CSS Selector
        
        value pattern in ===> css=(HTML tag)(.)(Value of Class attribute)
        
        ex: 
        
        
        3.Tag and Attribute
        
        value pattern in ===> css=(HTML Page)[Attribute=Value]
        
        ex: => <input type="phone" placeholder="Phone*" name="phone" value="" class="form-control sign-up-input-2 ">
               driver.findElement(By.cssSelector("input[name=\"phone\"]"))
               
        4.Tag, Class, and Attribute
        
        value pattern in ===> css=(HTML tag>)(. )(Class attribute value)([attribute=Value of attribute])
        
        ex: => <button type="submit" class="bg-lambda-900 hover:bg-transparent hover:text-lambda-900 border border-lambda-900 text-white ">Submit</button>
        
               driver.findElement(By.cssSelector("button.bg-lambda-900[type="submit"]"));
               
               
        5.Wild (*, ^ and $) in CSS for Classes
       
       
       
         8.xpath  
       
           syntax : driver.findElement(By.xpath(“xpathValue”));
         
         
           /// -- XPath locator in Selenium helps locate elements on the web page using XML expressions.
         
         
         1.Standard XPath:
         
           /// used ex: driver.findElement(By.xpath("//a[@class=\"nav-link\"]")).click();
       
                    value pattern in ===> XPath: //tagName[@attribute = 'value']
         
                 ex: => <input type="text" name="email" value="" placeholder="E-Mail Address" id="input-email" class="form-control">
         
                   /// The standard XPath of the desired WebElement is //input[@name= ’email’]. 
                   Here is how the XPath is used with the findElement() method to locate the element.
                    driver.findElement(By.xpath("//*[@id="input-email"]")); (need to check)
                   used in : driver.findElement(By.xpath("//*[@id=\"input-email\"]"));
 
       
          2.XPath Contains:
          
                value pattern in ===> //tagname[contains(@attribute, 'partial value of attribute')]
       
            /// ex: => <input type="text" placeholder="Full Name*" name="name" value="" class="form-control sign-up-input-2 ">
           
                    driver.findElement(By.xpath("//input[contains(@class, 'form-control')]"))
            
            
                    

            
             
    	  3.XPath Using AND and OR
    	    
    	      value pattern in ===> syntax : //input[@id='login_1' OR @name='login']
    	  
    	      value pattern in ===> syntax : //input[@id='login_1' AND @name='login']
    	  
    	   
    	    ///driver.findElement(By.xpath("//input[@id=\"input-email\" or @name=\"email\"]"));
    	     
    	    
    	    ///driver.findElement(By.xpath(""//input[@id=\"input-email\" AND @name=\"email\"]"));
    	  
    	    
    	  4.starts-with() Method in XPath
    	     
    	    value pattern in ===> //tagname[starts-with(@attribute,'starting name of the attribute value')]
    	     
    	    /// -- The starts-with() method in XPath is majorly used for locating WebElements whose value changes on the refresh of a page.


    	    ex: => <input type="password" name="password" value="" placeholder="Password" id="input-password" class="form-control">
    	     
    	           driver.findElement(By.xpath("//input[starts-with(@name,'pass')]"));
    	     
    	   5. XPath Text
    	   
    	      ///Text in the XPath locator in Selenium helps locate WebElements via XPath using exact text match. 
    	         It can be used when elements have to be located by looking into the tags containing certain text.
    	   
    	        value pattern in ===> //div[text()='Logged In']
    	     
    	     ex : => <button type="submit" class="bg-black hover:bg-transparent hover:text-black ">Submit&lt;/button>
    	     
    	              driver.findElement(By.xpath("//button[text()='Submit']"));
    	    
    	    
   
    	  
       
       */
       
    }
}



/*

package com.openlibrarytest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://openlibrary.org/");
		boolean logo=driver.findElement(By.xpath("//img[@class='logo-icon']")).isDisplayed();
		System.out.println("Logo is present"+logo);
		driver.findElement(By.name("q")).sendKeys("Games people play");
		driver.findElement(By.xpath("//input[@class='search-bar-submit']")).click();
		driver.findElement(By.xpath("//a[@title='This book is currently checked out, please check back later.' or @class='cta-btn cta-btn--ia cta-btn--available cta-btn--borrow']")).click();
		//driver.findElement(By.cssSelector("a[class='cta-btn cta-btn--ia cta-btn--available cta-btn--borrow']")).click();
		driver.findElement(By.id("username")).sendKeys("saiakash200236@gmail.com");
		driver.findElement(By.id("password")).sendKeys("S@i63akash");
		//driver.findElement(By.name("login")).click();
		//driver.findElement(By.linkText("Log In")).click();
		driver.findElement(By.cssSelector("button[class='cta-btn cta-btn--primary']")).click();
		driver.navigate().back();
		driver.navigate().back(); 
	}
}


package com.SeleniumTest;

package com.SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTest1 {

    public WebDriver driver;

    @BeforeTest
    public void launchApp() {
        driver = new ChromeDriver();
        driver.get("https://openlibrary.org/");
    }

    @Test
    public void checkLogo() throws InterruptedException {
        Thread.sleep(2000);
        boolean logo = driver.findElement(By.xpath("//img[@class='logo-icon']")).isDisplayed();
        System.out.println("Logo is present: " + logo);
    }

    @Test
    public void searchBook() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.name("q")).sendKeys("Games people play");
        driver.findElement(By.xpath("//input[@class='search-bar-submit']")).click();
    }

    @Test
    public void clickBorrow() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(@title, 'This book is currently checked out, please check back later.') or @class='cta-btn cta-btn--ia cta-btn--available cta-btn--borrow']")).click();
    }

    @Test
    public void loginValidation() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("saiakash200236@gmail.com");
        driver.findElement(By.id("password")).sendKeys("S@i63akash");
        driver.findElement(By.cssSelector("button[class='cta-btn cta-btn--primary']")).click();
    }

    @Test
    public void navigateBack() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().back();
        driver.navigate().back(); 
    }

    @AfterTest
    public void closeApp() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}


*/







