package com.assignment.steps;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.assignment.page.ProductSearchPage;
import com.assignment.utils.BaseClass;
import com.assignment.utils.WaitClass;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class AddToCart extends BaseClass {
    
    WebDriver driver;
    WaitClass waitclass;
    int item_1_final_cost = 0;
    int item_2_final_cost = 0;
    int total_cart_value = 0;
    int finalAmount = 0;
    int finalCost = 0;
    
    public AddToCart() throws IOException 
    {
        super();
        this.driver = BaseClass.getDriver();
        waitclass = new WaitClass(driver, 10);
    }

    public int addProductToCart(By product, By productPrice) throws InterruptedException
    {
        String parentHandle = driver.getWindowHandle(); 
        try {
            waitclass.waitForPageToLoad(this.driver);   
            waitclass.waitForElementVisible(product).click();

        } catch (StaleElementReferenceException e) {
               
            System.out.println("Retrying due to stale element");
            waitclass.waitForPageToLoad(this.driver);
            driver.findElement(product).click();
        }

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        
       for (String handle : tabs) { 
           if (!handle.equals(parentHandle)) { 

                driver.switchTo().window(handle);
                waitclass.waitForPageToLoad(driver);
                try {
                waitclass.waitForPageToLoad(this.driver); 
                String itemPriceText = waitclass.waitForElementVisible(productPrice).getText();
                String trimmedPrice = itemPriceText.replaceAll("₹", "").replace(",", "").trim();
                finalCost = Integer.parseInt(trimmedPrice);
                System.out.println("Item final cost: " + finalCost);
                waitclass.waitForPageToLoad(this.driver); 
                waitclass.waitForElementVisible(ProductSearchPage.addtoCartBtn).click();
                System.out.println("Add to cart is clicked successfully");
                waitclass.waitForPageToLoad(this.driver); 
                

                } catch (StaleElementReferenceException e) {
                    System.out.println("Retrying Add to Cart click");
                    waitclass.waitForPageToLoad(this.driver); 
                    driver.findElement(productPrice).getText();
                    waitclass.waitForPageToLoad(this.driver); 
                    driver.findElement(ProductSearchPage.addtoCartBtn).click();
                }
                driver.close();
            }
        }
        driver.switchTo().window(parentHandle);
        
    return finalCost;
    }
    @When("user click on the second available product in the list, and click on Add to cart")
    public void user_click_on_the_second_available_product_in_the_list_and_click_on_add_to_cart() throws InterruptedException {
        item_1_final_cost = addProductToCart(ProductSearchPage.item2, ProductSearchPage.item2Price);
    }
    
    @When("user click on the third available product in the list, and click on Add to cart.")
    public void user_click_on_the_third_available_product_in_the_list_and_click_on_add_to_cart() throws InterruptedException {
    item_2_final_cost = addProductToCart(ProductSearchPage.item3, ProductSearchPage.item3Price);   

}
@When("user goes to cart page and verify the total sum")
public void user_goes_to_cart_page_and_verify_the_total_sum() throws InterruptedException {
    String totalValue = "";
    try {
    
    waitclass.waitForElementVisible(ProductSearchPage.cart).click();
    waitclass.waitForPageToLoad(this.driver);
    totalValue = driver.findElement(ProductSearchPage.totalAmount).getText();
    }catch(StaleElementReferenceException e)
    {
        driver.findElement(ProductSearchPage.cart).click();
        driver.findElement(ProductSearchPage.totalAmount).getText();
    }
    String finalPrice = totalValue.replaceAll("₹", "");
    finalAmount = Integer.parseInt(finalPrice);

    String platfromFees= driver.findElement(ProductSearchPage.platformFee).getText();
    String trim_platfromFees_value= platfromFees.replaceAll("₹", "");
    int finalPlatfromFeeValue = Integer.parseInt(trim_platfromFees_value);
    
    total_cart_value = item_1_final_cost + item_2_final_cost+finalPlatfromFeeValue;

    System.out.println("Calculated Total Cart Value: " + total_cart_value);
    System.out.println("Displayed Total Cart Value: " + finalAmount);
}
@Then("total sum shoud be validate")
public void total_sum_shoud_be_validate() {
    Assert.assertEquals(finalAmount, total_cart_value, 
                "Total sum validation failed: The calculated and displayed totals do not match!");
        System.out.println("✅ Passed: Total sum validation successful.");
}
}
