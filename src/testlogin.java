
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class testlogin {

	 private static final String WEB_PAGE = "https://courses.ultimateqa.com/users/sign_in";
	 WebDriver browser;
	@BeforeClass
	public static void parameters()
	{
		 System.setProperty("webdriver.chrome.driver", "/Users/me-mac/Documents/college achievements/QualityAssurance/sel/chromedriver");
	}
	
	@Before
	public void openBrowser()
	{
		  browser = new ChromeDriver();
	      browser.manage().window().fullscreen();
	      browser.get(WEB_PAGE);
	      waitAMoment(2);
	}
	@After
	public void CloseBrowser()
	{
		browser.quit();
	}
	
	@Test
	public void TestInvalidEmailAndPassword() {
		 //Test invalid email and password
        insertCredentials(browser, "AlaMaKota@gmail.com", "sdfndsffga");
        clickLogin(browser);
        waitAMoment(2);
        verifyLoggedIn(browser);
        Assert.assertEquals(false,  verifyLoggedIn(browser));
	}
	

	@Test
	public void TestInvalidEmail() {
		 //Test invalid email
        insertCredentials(browser, "AlaMaKota@gmail.com", "alamakota");
        clickLogin(browser);
        waitAMoment(2);
        Assert.assertEquals(false,  verifyLoggedIn(browser));
	}

	@Test
	public void TestInvalidPassword() {
		 //Test invalid password
        insertCredentials(browser, "hehoxax889@rubeshi.com", "sdfndsffga");
        clickLogin(browser);
        waitAMoment(2);
        Assert.assertEquals(false,  verifyLoggedIn(browser));
	}

	@Test
	public void TestValidCredentials() {
		   //Test valid credentials
        insertCredentials(browser, "hehoxax889@rubeshi.com", "alamakota");
        clickLogin(browser);
        waitAMoment(5);
        Assert.assertEquals(true,  verifyLoggedIn(browser));
	}
	
	
	
	private static void insertCredentials(WebDriver browser, String login, String password) {
        WebElement elementEmail = browser.findElement(By.id("user[email]"));
        elementEmail.sendKeys(login);

        WebElement elementPassword = browser.findElement(By.id("user[password]"));
        elementPassword.sendKeys(password);

        if(elementEmail.getAttribute("value").equals(login)) {
            System.out.println("Email has been written correctly");
        } else {
            System.out.println("Email has not been written correctly");
        }

        if(elementPassword.getAttribute("value").equals(password)) {
            System.out.println("Password has been written correctly");
        } else {
            System.out.println("Password has not been written correctly");
        }
    }

    private static void clickLogin(WebDriver browser) {
        browser.findElement(By.id("user[password]")).sendKeys(Keys.ENTER);
    }

    private static boolean verifyLoggedIn(WebDriver browser) {
        try {
        	browser.findElement(By.xpath("//*[@id='header-dropdown-menu']/li[2]/a"));            
        	System.out.println("User logged in");
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("User not logged in");
            browser.navigate().refresh();
            waitAMoment(2); 
            return false;
        }

    }

    private static void waitAMoment(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	

}
