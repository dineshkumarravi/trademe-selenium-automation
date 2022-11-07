package com.stepdefinitions;

import com.utils.CommonUtils;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CucumberHooks {

    private static WebDriver driver;

    /**
     * Initialize the browser driver instance.
     */
    @Before
    public void initialize(Scenario scenario) throws Exception {
        driver = CommonUtils.initializeDriver();
    }

    /**
     * Quit the browser driver instance.
     */
    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            CommonUtils.getScreenshot(driver, scenario.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }
}

