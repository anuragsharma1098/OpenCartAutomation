# OpenCart Automation Test Suite

A comprehensive Selenium-based test automation framework for OpenCart e-commerce platform using Java, Maven, and TestNG.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Project Setup](#project-setup)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Project Structure](#project-structure)
- [Test Reports](#test-reports)

## Prerequisites

Before running the tests, ensure you have the following installed:

- **Java Development Kit (JDK)**: Version 8 or higher
- **Maven**: Version 3.6 or higher
- **Git**: For version control
- **Chrome/Firefox Browser**: For running tests
- **ChromeDriver/GeckoDriver**: Web driver for browser automation (automatically managed by Maven)

## Project Setup

### 1. Clone the Repository
```bash
git clone https://github.com/anuragsharma1098/OpenCartAutomation_java_sel.git
cd OpenCartAutomation
```

### 2. Install Dependencies
```bash
mvn clean install
```

This command will:
- Clean the project
- Download all required dependencies
- Compile the source code
- Install the project

## Configuration

### Update Configuration Properties
Edit `src/test/resources/config.properties` to configure:

```properties
# Browser Configuration
browser=chrome
# or
browser=firefox

# Base URL
baseURL=http://opencart-demo-url

# Timeout (in seconds)
timeout=10

# Log Level
logLevel=INFO
```

### Test Data
Place test data files in the `testData/` directory. The framework uses Excel files for data-driven testing.

## Running Tests

### Run All Tests
```bash
mvn test
```

### Run Specific Test Suite
```bash
mvn test -Dsuites=master.xml
```

### Run Specific Test Class
```bash
mvn test -Dtest=TC001_AccountRegistrationTest
```

### Run Tests with Specific Browser
```bash
mvn test -Dbrowser=firefox
```

### Using Batch File (Windows)
```bash
./run.bat
```

### Run with Cross-Browser Testing
```bash
mvn test -Dsuites=crossbrowsertesting.xml
```

### Run with Docker Grid
```bash
mvn test -Dsuites=grid_docker.xml
```

## Project Structure

```
OpenCartAutomation/
├── src/
│   ├── main/                          # Main source code
│   └── test/
│       ├── java/
│       │   ├── pageObjects/           # Page Object Model classes
│       │   │   ├── BasePage.java
│       │   │   ├── HomePage.java
│       │   │   ├── LoginPage.java
│       │   │   ├── AccountRegistrationPage.java
│       │   │   └── MyAccountPage.java
│       │   ├── testBase/              # Base test configuration
│       │   │   └── BaseClass.java
│       │   ├── testCases/             # Test case classes
│       │   │   ├── TC001_AccountRegistrationTest.java
│       │   │   ├── TC002_LoginTest.java
│       │   │   └── TC003_LoginTestDDT.java
│       │   └── utilities/             # Utility classes
│       │       ├── DataProviders.java
│       │       ├── ExcelUtility.java
│       │       └── ExtentReportUtility.java
│       └── resources/
│           ├── config.properties      # Configuration file
│           └── log4j2.xml             # Logging configuration
├── testData/                          # Test data files (Excel)
├── reports/                           # Test execution reports
├── logs/                              # Application logs
├── pom.xml                            # Maven configuration
├── master.xml                         # Main test suite
├── crossbrowsertesting.xml            # Cross-browser test suite
├── grid_docker.xml                    # Docker grid test suite
└── docker-compose.yaml                # Docker composition for Selenium Grid

```

## Test Reports

### HTML Reports
After test execution, view the HTML reports in the `reports/` directory:
- `Test_Report_YYYY.MM.DD.HH.MM.SS.html` - Extent Report with detailed test results

### TestNG Reports
TestNG reports are generated in `target/surefire-reports/`:
- `index.html` - Main test report
- `emailable-report.html` - Email-friendly report

### View Reports
Open any HTML report in a web browser to view:
- Test execution status
- Pass/Fail statistics
- Detailed test logs
- Screenshots (if captured)

## Test Execution Flow

1. **Setup**: Browser initialization and configuration loading
2. **Pre-conditions**: Login and navigation to required page
3. **Test Execution**: Performing the actual test steps
4. **Verification**: Asserting expected vs actual results
5. **Teardown**: Browser closure and resource cleanup
6. **Reporting**: Results captured in Extent Reports and TestNG reports

## Page Object Model (POM)

This framework follows the Page Object Model design pattern:
- Each page has a corresponding Page class
- Page classes contain page elements as properties
- Page classes contain methods to interact with those elements
- Test classes use page objects to perform actions

Example:
```java
HomePage homePage = new HomePage(driver);
homePage.clickLoginLink();
LoginPage loginPage = new LoginPage(driver);
loginPage.enterUsername("test@example.com");
loginPage.enterPassword("password");
loginPage.clickLoginButton();
```

## Data-Driven Testing

Test data is managed using Excel files:
- Located in `testData/` directory
- Used with `@DataProvider` annotations
- Enables running single test with multiple data sets

## Logging

Logging is configured in `src/test/resources/log4j2.xml`:
- Logs are written to console and log files
- Log files are stored in `logs/` directory
- Configurable log levels: DEBUG, INFO, WARN, ERROR

## Docker & Selenium Grid

### Start Selenium Grid with Docker
```bash
docker-compose up -d
```

### Run Tests Against Grid
```bash
mvn test -Dsuites=grid_docker.xml
```

### Stop Docker Services
```bash
docker-compose down
```

## Troubleshooting

### Issue: ChromeDriver version mismatch
**Solution**: Update ChromeDriver in pom.xml to match your Chrome browser version.

### Issue: Tests not running
**Solution**: Ensure:
- Java and Maven are properly installed
- `config.properties` has correct baseURL
- Browser is installed on your machine

### Issue: Port already in use (Docker)
**Solution**: 
```bash
docker-compose down
# Or change port in docker-compose.yaml
```

## Best Practices

1. **Use Page Object Model** - Keep UI interactions in page classes
2. **Data-Driven Testing** - Use Excel for test data
3. **Explicit Waits** - Use WebDriverWait instead of Thread.sleep()
4. **Error Handling** - Implement proper exception handling
5. **Test Independence** - Tests should not depend on each other
6. **Screenshots on Failure** - Capture for debugging

## Contributing

1. Create a new branch for each feature
2. Follow the existing code structure
3. Write meaningful test names
4. Update reports with new tests

## Support

For issues or questions, please create a GitHub issue in the repository.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
