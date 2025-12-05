package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	// Constructor
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	// Locators
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement headingMyAccount;

	// Methods
	public boolean isMyAccountPageDisplayed() {
		try {
			return headingMyAccount.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
