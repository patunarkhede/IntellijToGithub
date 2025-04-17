package com.actitimeautomation.sample;



import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
        import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
        import org.testng.Assert;
import org.testng.annotations.*;

        import java.time.Duration;

public class punchT {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testffc.nimapinfotech.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void performLogin(String email, String password) {
        // Wait for loader to disappear (optional)
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
        } catch (TimeoutException ignored) {}

        // Wait for Email field
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Email']")));
        emailInput.sendKeys(email);

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Password']")));
        passwordInput.sendKeys(password);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Sign In')]")));
        signInButton.click();

  
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("dashboard"),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Punch In')]"))
        ));
    }

    @Test
    public void verifyPunchInToast() {
        performLogin("Pnarkhede@gmail.com", "Narkhede@123");

        WebElement punchInBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Punch In')]")));
        punchInBtn.click();

        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("Toastify__toast-body")));
        String toastText = toast.getText();
        System.out.println("Toast message: " + toastText);

        Assert.assertTrue(
                toastText.contains("Punch In Successfully") || toastText.contains("already Punch In"),
                "❌ Toast validation failed! Actual toast: " + toastText
        );
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


