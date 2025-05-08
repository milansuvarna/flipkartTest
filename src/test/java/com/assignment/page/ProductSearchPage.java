package com.assignment.page;

import org.openqa.selenium.By;

public class ProductSearchPage {
    
    public static By searchBox = By.cssSelector("input[placeholder='Search for Products, Brands and More']");
    public static By searchButton = By.cssSelector("button[type='submit']");
    public static By lowToHigh = By.xpath("//div[normalize-space()='Price -- Low to High']");
    public static By price = By.xpath("//div[@class='hl05eU']/div[1]");
    public static By pageNavNext = By.xpath("//a[@class='_9QVEpD']");
   public static By bottomPageText = By.xpath("//span[contains(text(),'Page 1 of')]"); 

    public static By item2 = By.xpath("(//a[@class='rPDeLR'])[2]");
    public static By item3 = By.xpath("(//a[@class='rPDeLR'])[3]");
    public static By addtoCartBtn = By.xpath("//button[contains(text(),'Add to cart')]");
    public static By item2Price = By.xpath("//div[@class='Nx9bqj CxhGGd']");
    public static By item3Price = By.xpath("//div[@class='Nx9bqj CxhGGd']");
    public static By totalAmount = By.cssSelector("div[class='PWd9A7 xvz6eC'] span");
    public static By cart = By.xpath("//span[contains(text(),'Cart')]");
    public static By platformFee = By.xpath("(//span[@class='b5rp0W'])[3]");
    
}
