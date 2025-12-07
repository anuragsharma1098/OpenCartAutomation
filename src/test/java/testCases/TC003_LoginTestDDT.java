package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginTestDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String user, String pwd, String exp) {

		logger.info("Starting TC003_LoginTestDDT");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			LoginPage lp = new LoginPage(driver);
			lp.setUsername(user);
			lp.setPassword(pwd);
			lp.clickLogin();

			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageDisplayed();
			if (exp.equalsIgnoreCase("Valid")) {
				if (targetPage) {
					logger.info("Login test passed");
					macc.clickLogout();
					Assert.assertTrue(true);
				} else {
					logger.error("Login test failed");
					Assert.assertTrue(false);
				}
			}
			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetPage) {
					logger.error("Login test failed");
					macc.clickLogout();
					Assert.assertTrue(false);
				} else {
					logger.info("Login test passed");
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {
			logger.fatal("Login test failed due to exception: " + e.getMessage());
			Assert.fail();
		}
		logger.info("Finished TC003_LoginTestDDT");
	}

}
