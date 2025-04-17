package com.actitimeautomation.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class AddC {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        // No need to set the path if using WebDriverManager or already in PATH
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testffc.nimapinfotech.com");
    }

    @Test
    public void addCustomerTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-0")));
        emailField.sendKeys("7066310138");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-1")));
        passwordField.sendKeys("Narkhede@123");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign In')]")));
        loginButton.click();

     
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Dashboard')]")));

  
        WebElement myCustomersBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(),'My Customers')]")));
        myCustomersBtn.click();



        WebElement newCustomerBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),' New Customer ')]")));
        newCustomerBtn.click();



       


        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mat-input-27")));
        inputField.sendKeys("Patu Narkhede");



        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),' Save ')]")));
        saveBtn.click();

        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar")));
        System.out.println("Toast message: " + toast.getText());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
