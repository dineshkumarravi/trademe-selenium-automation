package com.stepdefinitions;


import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.pages.SearchCar.CarSearchPage;
import com.utils.CommonUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CarMakeApiSteps {

    public final WebDriver driver = CommonUtils.getDriverInstance();
    private static Response response;
    private static String jsonString;
    private static List<Map<String, String>> noOfCarsMakeFromApi;
    private static final String BASE_API_URL = "https://api.trademe.co.nz";
    private String categoryNumber = "0001-0268";

    public CarSearchPage carSearchPage;

    public CarMakeApiSteps() {
        carSearchPage = new CarSearchPage(driver);
    }

    @Given("I fetch the car make details by sending api request")
    public void i_fetch_the_car_make_details_by_sending_api_request() {
        RestAssured.baseURI = BASE_API_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/v1/Categories/" + categoryNumber + "-.json");
        jsonString = response.asString();
        noOfCarsMakeFromApi = JsonPath.from(jsonString).get("Subcategories");
    }

    @Then("the response will return status as {int}")
    public void the_response_will_return_status(int status) {
        Assert.assertEquals(response.statusCode(), status);
    }

    @Then("I can verify number of named car makes count")
    public void i_can_verify_number_of_named_cars_make_count() {
        Assert.assertTrue(noOfCarsMakeFromApi.size() > 0);
        System.out.println("The number of named car make count from API are " + (noOfCarsMakeFromApi.size() - 1));
        //Compare api count with web count
        Assert.assertEquals(noOfCarsMakeFromApi.size() - 1, carSearchPage.getCarMakeCount());
    }
}

