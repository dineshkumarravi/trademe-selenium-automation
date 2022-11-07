package com.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

public class CommonUtils {

    private static WebDriver driver;
    public static Properties props;

    /**
     * Initialize driver instance based on browser type
     */
    public static WebDriver initializeDriver() {
        try {
            props = new Properties();
            String path = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
            FileInputStream fs = new FileInputStream(path);
            props.load(fs);
            ChromeOptions option = new ChromeOptions();
            option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            switch (props.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(option);
                    break;
                case "iOS":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new Exception("Invalid Browser! - " + props.getProperty("browser"));
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(props.getProperty("url"));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }

    public static WebDriver getDriverInstance() {
        return driver;
    }

    /**
     * Get the screenshot file
     *
     * @param driver
     * @param screenshotName
     */
    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File source = scrShot.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
                + ".jpeg";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}