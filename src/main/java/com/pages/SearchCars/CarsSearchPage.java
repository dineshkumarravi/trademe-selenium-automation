package com.pages.SearchCars;

import com.utils.PageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cars Search page class method implementation using page factory.
 *
 * @author Dineshkumar.
 */
public class CarsSearchPage extends PageUtils {

    public HashMap<String, String> carMakeCountDetails = new HashMap<String, String>();
    public WebDriver driver;

    @FindBy(xpath = "(//img[contains(@title,'Trade Me')])[1]")
    private WebElement logoTradeMe;
    @FindBy(linkText = "Motors")
    private WebElement menuMotors;
    @FindBy(xpath = "//tg-select-container[contains(@label,'Make')]//following-sibling::div")
    private WebElement makeCarDropDown;
    @FindBy(xpath = "//div[contains(text(),'Any make')]//parent::div//preceding::option")
    private List<WebElement> makeCarOptions;
    @FindBy(xpath = "//button[contains(text(),'Search')]")
    private WebElement btnSearch;
    @FindBy(css = "tm-search-header-result-count>h3")
    private WebElement carResultsCount;
    @FindBy(xpath = "//span[contains(text(),'Cars')]")
    private WebElement carMenu;

    /**
     * Initialize page elements
     */
    public CarsSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * verify the trade me logo in home page
     */
    public void isTradeMeLogoDisplayed() {
        waitForVisibility(logoTradeMe);
        logoTradeMe.isDisplayed();
    }

    /**
     * Select the search category menu.
     *
     * @param menuOption pass the menu option from test.
     */
    public void clickMotorMenu(String menuOption) {
        WebElement menuMotors = driver.findElement(By.linkText(menuOption));
        menuMotors.click();
    }

    /**
     * select the make car dropdown
     */
    public void clickMakeCarDropdown() {
        waitForVisibility(carMenu);
        click(makeCarDropDown);
    }

    /**
     * Get the car make count from make dropdown excludes Any Make and other values
     */
    public int getCarMakeCount() {
        waitForVisibility(carMenu);
        return makeCarOptions.size() - 2;
    }

    /**
     * Search the car results by select the value in make dropdown.
     * Once the results are captured returning to cars search page.
     * @param makeCar pass the car make value from test.
     */
    public void searchCarMakeResultsAndBack(List<String> makeCar) throws InterruptedException {
        waitForVisibility(carMenu);
        for (String car : makeCar) {
            click(makeCarDropDown);
            for (WebElement makeOption : makeCarOptions) {
                if (makeOption.getText().equals(car)) {
                    makeOption.click();
                    clickSearch();
                    carMakeCountDetails.put(car, String.valueOf(getShowingCarResultsCount(car)));
                    browserBackButton(driver);
                    break;
                }
            }
        }
    }

    /**
     * click the search button in car search tab
     */
    public void clickSearch() {
        click(btnSearch);
    }

    /**
     * Get the car results count after search
     *
     * @param car pass the car make value.
     */
    public int getShowingCarResultsCount(String car) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement carMakeHeaderText = driver.findElement(By.xpath("//tm-search-header-heading/h1[contains(text(),'" + car + " for sale')]"));
            waitForVisibility(carMakeHeaderText);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(carResultsCount.getText().split(" ")[1].replace(",", ""));
    }

    /**
     * Show the results count with respect to each car make
     */
    public HashMap<String, String> getCarsSearchResultsCount() {
        for (Map.Entry<String, String> set :
                carMakeCountDetails.entrySet()) {
            // Printing all elements of a Map
            System.out.println(set.getKey() + " = " + set.getValue());
        }
        return carMakeCountDetails;
    }
}