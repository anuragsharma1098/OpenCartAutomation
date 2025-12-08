package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups = { "regression", "master" })
	public void verifyRegistration() {
		logger.info("***** Starting TC001_AccountRegistrationTest *****");
		try {
			// Code to verify account registration
			HomePage homePage = new HomePage(driver);
			homePage.clickMyAccount();
			logger.info("Clicked on My Account link");
			homePage.clickRegister();
			logger.info("Clicked on Register link");
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("Providing customer data");
			// regPage.setFirstName("John");
			regPage.setFirstName(randomString().toUpperCase());
			// regPage.setLastName("Doe");
			regPage.setLastName(randomString().toUpperCase());
			// regPage.setEmail("johndoes_123456@gmail.com");
			regPage.setEmail(randomString() + "@gmail.com");
			// regPage.setTelephone("1234567890");
			regPage.setTelephone(randomNumber());
			// regPage.setPassword("xyz123456");
			String pwd = randomAlphaNumeric();
			regPage.setPassword(pwd);
			// regPage.setConfirmPassword("xyz123456");
			regPage.setConfirmPassword(pwd);
			regPage.checkPrivacyPolicy();
			regPage.clickContinue();
			logger.info("Clicked on Continue button");

			String confmsg = regPage.getConfirmationMsg();
			if (confmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
				logger.info("Account registration successful");
			} else {
				Assert.assertTrue(false);
				logger.error("Account registration failed");
				logger.debug("Expected confirmation message not received");
			}
			// Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		} catch (Exception e) {
			logger.error("Error occurred during registration: " + e.getMessage());
			logger.debug("Error details: ", e.getMessage());
			Assert.fail("Test case failed due to exception: " + e.getMessage());
		}

		logger.info("***** Finished TC001_AccountRegistrationTest *****");
	}

}
