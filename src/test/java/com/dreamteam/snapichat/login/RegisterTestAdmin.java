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
public class RegisterTestAdmin {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.out.println(" > Register Test Admin");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8084";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testRegisterTestAdmin() throws Exception {
        driver.get(baseUrl + "/SnapiChat/index.jsp");
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.id("signup-link")).click();
        driver.findElement(By.id("user-fname")).clear();
        driver.findElement(By.id("user-fname")).sendKeys("Admin");
        driver.findElement(By.id("user-lname")).clear();
        driver.findElement(By.id("user-lname")).sendKeys("Test");
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("test_admin");
        driver.findElement(By.id("user-fname")).clear();
        driver.findElement(By.id("user-fname")).sendKeys("Test");
        driver.findElement(By.id("user-pw")).clear();
        driver.findElement(By.id("user-pw")).sendKeys("test_admin");
        driver.findElement(By.id("user-pw-repeat")).clear();
        driver.findElement(By.id("user-pw-repeat")).sendKeys("test_admin");
        driver.findElement(By.id("user-email")).clear();
        driver.findElement(By.id("user-email")).sendKeys("admin@unit.gr");
        driver.findElement(By.name("commit")).click();
        try {
            assertEquals("Welcome", driver.findElement(By.cssSelector("h4")).getText());
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
