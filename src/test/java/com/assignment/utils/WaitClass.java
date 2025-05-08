package com.assignment.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitClass {
    
     private WebDriver driver;
     private WebDriverWait wait;
    
    public WaitClass(WebDriver driver, int timeout)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }
   
    public WebElement waitForElementVisible(By locators)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locators));

    }

    public void waitForPageToLoad(WebDriver driver) throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .equals("complete"));
                        Thread.sleep(2000);
    }


   
}
