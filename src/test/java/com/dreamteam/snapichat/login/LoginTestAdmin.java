package com.dreamteam.snapichat.login;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Natasa
 */
public class LoginTestAdmin {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.out.println(" > Login Test Admin");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:9090";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginTestAdmin() throws Exception {
        driver.get(baseUrl + "/SnapiChat/");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.xpath("(//input[@id='user-name'])[2]")).clear();
        driver.findElement(By.xpath("(//input[@id='user-name'])[2]")).sendKeys("test_admin");
        driver.findElement(By.cssSelector("div.input.full > #user-pw")).clear();
        driver.findElement(By.cssSelector("div.input.full > #user-pw")).sendKeys("test_admin");
        driver.findElement(By.xpath("(//input[@name='commit'])[2]")).click();
        try {
            assertEquals("test_admin", driver.findElement(By.id("user-name")).getAttribute("value"));
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
