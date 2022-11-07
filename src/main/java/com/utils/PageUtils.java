package com.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/**
 * Common web elements methods implementation for Page class.
 *
 * @author Dineshkumar.
 */
public class PageUtils {

    public static WebDriver driver = CommonUtils.getDriverInstance();

    /**
     * Click on web element.
     *
     * @param element
     */
    public static void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    /**
     * Wait for visibility of element.
     *
     * @param element
     */
    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void browserBackButton(WebDriver driver) {
        driver.navigate().back();
    }
}