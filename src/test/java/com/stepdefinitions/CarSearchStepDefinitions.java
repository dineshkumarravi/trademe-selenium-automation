package com.stepdefinitions;

import com.pages.SearchCars.CarsSearchPage;
import com.utils.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class CarSearchStepDefinitions {

    public final WebDriver driver = CommonUtils.getDriverInstance();
    public CarsSearchPage carSearchPage;

    public CarSearchStepDefinitions() {
        carSearchPage = new CarsSearchPage(driver);
    }

    @Given("I am on the trademe webpage to choose the motors search option")
    public void i_am_on_the_trademe_webpage_to_choose_the_motors_search_option() {
        carSearchPage.isTradeMeLogoDisplayed();
    }

    @And("I go to the Cars search page under {string}")
    public void i_go_to_the_cars_search_page_under(String menu) {
        carSearchPage.clickMotorMenu(menu);
    }

    @When("I click Make dropdown")
    public void i_click_make_dropdown() {
        carSearchPage.clickMakeCarDropdown();
    }

    @Then("I can verify number of named car makes count as {int}")
    public void i_can_verify_named_make_cars_countAs(int count) {
        int noOfNamedCarMakes = carSearchPage.getCarMakeCount();
        Assert.assertEquals(noOfNamedCarMakes, count);
    }

    @And("I select make option")
    public void i_select_option(List<String> make) throws InterruptedException {
        carSearchPage.searchCarMakeResultsAndBack(make);
    }

    @When("I click Search button")
    public void i_click_search_button() {
        carSearchPage.clickSearch();

    }

    @Then("I can see the number of cars search results count")
    public void i_can_see_named_make_cars_search_results_count() {
        carSearchPage.getCarsSearchResultsCount();
    }
}
