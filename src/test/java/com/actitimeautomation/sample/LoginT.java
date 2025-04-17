
package com.actitimeautomation.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginT {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testffc.nimapinfotech.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void performLogin(String email, String password) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='email' or @placeholder='Email']")));
        emailInput.sendKeys(email);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='password' or @placeholder='Password']")));
        passwordInput.sendKeys(password);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Sign In')]")));
        signInButton.click();

     
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"Pnarkhede@gmail.com", "Narkhede@123"},  // Valid
                {"invalid@example.com", "wrongpass"}      // Invalid
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password) {
        try {
            performLogin(email, password);

            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("dashboard")) {
                System.out.println("✅ Login success for: " + email);
            } else {
                System.out.println("❌ Login failed for: " + email);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Exception occurred for: " + email + " → " + e.getMessage());
        }
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
