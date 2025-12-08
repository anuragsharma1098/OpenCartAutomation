package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = { "sanity", "master" })
	public void verifyLogin() {
		logger.info("***** Starting TC002_LoginTest *****");
		try {
			// Code to verify login functionality
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("Clicked on My Account link");
			homePage.clickLogin();
			logger.info("Clicked on Login link");
			LoginPage loginPage = new LoginPage(driver);
			logger.info("Providing login credentials");
			loginPage.setUsername(configProp.getProperty("username"));
			loginPage.setPassword(configProp.getProperty("password"));
			loginPage.clickLogin();
			logger.info("Clicked on Login button");

			// MyAccount page object
			MyAccountPage myAccountPage = new MyAccountPage(driver);
			boolean targetPage = myAccountPage.isMyAccountPageDisplayed();
			Assert.assertTrue(targetPage, "Login failed - My Account page not displayed");
		} catch (Exception e) {
			logger.error("Error occurred during login: " + e.getMessage());
			logger.debug("Error details: ", e.getMessage());
			Assert.fail("Test case failed due to exception: " + e.getMessage());
		}

		logger.info("***** Finished TC002_LoginTest *****");
	}

}
