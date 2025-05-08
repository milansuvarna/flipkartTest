package com.assignment.runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = { "src/test/resources/features/1_sort.feature",
                "src/test/resources/features/2_addToCart.feature"
            
},
    glue = "com.assignment.steps",
    plugin = { "pretty", "html:target/cucumber-reports.html" }
    
)
@Test
public class TestRunner extends AbstractTestNGCucumberTests {

     @AfterSuite
    public void cleanUp() {
        System.out.println("All tests completed. Closing browser.");
        com.assignment.utils.BaseClass.tearDown();
    }
}

