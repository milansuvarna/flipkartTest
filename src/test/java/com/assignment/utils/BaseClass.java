package com.assignment.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;

    public static void loadConfig() throws FileNotFoundException, IOException
    
    {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);

    }

    public static void initializeDriver() throws IOException
    {
        loadConfig();
        String browser = prop.getProperty("browser");
        String baseUrl = prop.getProperty("baseUrl");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get(baseUrl);

    }
    
    public static WebDriver getDriver() {
        return driver;
    }
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
