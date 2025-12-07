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

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement linkLogout;

	// Methods
	public boolean isMyAccountPageDisplayed() {
		try {
			return headingMyAccount.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickLogout() {
		try {
			linkLogout.click();
		} catch (Exception e) {
			System.out.println("Logout link not found: " + e.getMessage());
		}
	}
}
