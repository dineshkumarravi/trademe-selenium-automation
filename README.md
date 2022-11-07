

# *Trade Me- Test Automation Solution using Selenium with Java & RestAssured, Cucumber-BDD, Page Object model approach, TestNG and Maven* 

**(1) First we have to copy git repository on our local system and given the below commands from command line(cmd) for execution:**

```
git clone https://github.com/dineshkumarravi/trademe-selenium-automation.git
cd trademe-selenium-automation
mvn clean test

```
**(2) Necessary Libraries and Software on Windows machine:**

```
(1) Install Java - openjdk11 and Maven
(2) Install Intelij IDE

```
**(3) Project Structure:**
```
  * src/main/java
    - pages.SearchCars
    - utils
  * src/main/resources
    - config.properties 
  * src/test/java
    - runner
  * src/test/java
    - stepdefinitions
  * src/test/resources
       - features - Cars Make search
            *It consists of the following Two web tests and One Api test scenarios
                (1) To verify the number of named car makes count in Make dropdown
                (2) To verify cars results count based on make search
                (3) To verify the number of named car makes count using Trade Me Api
      
  * pom.xml - maven dependencies    
```
**(4) Run test from different areas:**
```
* Once we copy this project on local system either we can run the test cases from command line using mvn clean test
  or from any IDE of your preference.
  
* We can also run from RunnerTest.java file where right click on it and run as a RunnerTest.

* #Report will be generated inside **target folder. 
  **For test report,cucumber-reports.html file will be generated after every run.
  
* If you want to see any failure report then you have to change id or xpath in the tests or pages. It will
  capture failed screenshot in FailedTestsScreenshots folder after every scenario runs and can see error traces in the report.
