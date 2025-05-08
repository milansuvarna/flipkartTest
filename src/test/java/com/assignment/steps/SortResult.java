package com.assignment.steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.assignment.page.ProductSearchPage;
import com.assignment.utils.BaseClass;
import com.assignment.utils.WaitClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortResult extends BaseClass {
    WaitClass waitclass;

    public SortResult() throws IOException
    {
        initializeDriver();
        waitclass = new WaitClass(driver, 10);
    }

    @Given("user open Flipkart homepage")
    public void user_open_flipkart_homepage() throws IOException 
    {
        System.out.println("Flipkart homepage opened.");
    }
    
    @When("the user search for {string}")
    public void the_user_search_for(String string) 
    {
        waitclass.waitForElementVisible(ProductSearchPage.searchBox);
        driver.findElement(ProductSearchPage.searchBox).sendKeys(string);
        driver.findElement(ProductSearchPage.searchButton).click();
    }
    @When("user click on {string} option")
    public void user_click_on_option(String string) throws InterruptedException 
    {
        if (string.equals("lowToHigh"))
        {
            try {
                waitclass.waitForElementVisible(ProductSearchPage.lowToHigh);
                Thread.sleep(2000);
                WebElement element = driver.findElement(ProductSearchPage.lowToHigh);
                element.click();
            } catch (StaleElementReferenceException e) {
                WebElement element = driver.findElement(ProductSearchPage.lowToHigh); // Re-locate
                element.click();
            }
        }
}
    @Then("the user validate that the prices are sorted on page {int} and page {int}")
    public void the_user_validate_that_the_prices_are_sorted_on_page_and_page(Integer int1, Integer int2)
            throws InterruptedException {

        List<Integer> sortedPrice = new ArrayList<>();
        for (int i = 1; i <= int2; i++) {
            List<WebElement> allPrice = new ArrayList<>();
            try
            {
            waitclass.waitForElementVisible(ProductSearchPage.price);
            allPrice = driver.findElements(ProductSearchPage.price);
            waitclass.waitForPageToLoad(driver);
            for (WebElement priceElement : allPrice) {
                
                String priceText1 = priceElement.getText().replace("₹", "");
                String priceText=priceText1.replace(",", "").trim();
                
                int finalPrice = Integer.parseInt(priceText);
                sortedPrice.add(finalPrice);
            }
            }
            catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            if (i < int2) {
                try {

                    
                WebElement paginationBtn = driver.findElement(ProductSearchPage.bottomPageText);
                Point buttonLocation = paginationBtn.getLocation();
                int x = buttonLocation.getX();
                int y = buttonLocation.getY()-1000;

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(arguments[0], arguments[1]);", x, y);
                Thread.sleep(3000);
                driver.findElement(ProductSearchPage.pageNavNext).click();

            } catch (StaleElementReferenceException e) {
                    e.printStackTrace();
                }
            }
        }
        boolean sorted = true;
        for (int j = 0; j < sortedPrice.size() - 1; j++) {
            if (sortedPrice.get(j) > sortedPrice.get(j + 1)) {
                System.out.println("Error: " + sortedPrice.get(j) + " -> " + sortedPrice.get(j + 1));
                sorted = false;
            }
        }
        Assert.assertTrue(sorted, "Prices are not sorted in ascending order.");
        if (sorted) {
            System.out.println("Success: All prices are sorted in ascending order.");
        } else {
            System.out.println("Error: Prices are not sorted correctly.");
        }
    }
}
