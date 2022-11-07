package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@WebTest or @APITest"
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}