# My Test Automation Project

This project uses:
- Java
- Maven
- Cucumber 7
- TestNG
- LambdaTest for execution, supports parallel execution

## How to Run
- Add your Lamdatest cred in config.properties present at src/main/resources/config.properties
- Go to testNg.xml file and run it from there by right clicking and run as .xml
- Click add configuration in intellij and add the configuration of testNG, set the path and click run
- If want to run via terminal and maven installed in system, use this command mvn test -Dcucumber.filter.tags="@yourTag"

## external files
- testData.json (for test data )
- deviceConfig.json (on which device you want to run)

## Failure screenshots
- Failure screenshots can be seen in root/screenshots folder

## Reports
- To see the generated report, go to root/ExtentReports

## Sample output folder has been created and sample images for pass and fail scenario has been added 
