package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test
	public void verifyRegistration() {
		// Code to verify account registration
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.clickRegister();
		AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
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

		String confmsg = regPage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}

}
