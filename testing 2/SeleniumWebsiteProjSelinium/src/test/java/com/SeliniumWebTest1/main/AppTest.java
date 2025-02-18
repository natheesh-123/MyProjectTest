package com.SeliniumWebTest1.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	

	 private WebDriver driver;
	 
//	 
//	 @Before
//	    public void setUp() {
//	        // Set the path to your ChromeDriver executable
//	        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
//	        driver = new ChromeDriver();
//	        driver.manage().window().maximize();
//	    }

	 @BeforeTest
		public void LaunchBrowser() {
			System.out.println("Before test");
			driver = new ChromeDriver();
			driver.get("https://www.selenium.dev/");
			driver.manage().window().maximize();
		}
	
	
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }
}
