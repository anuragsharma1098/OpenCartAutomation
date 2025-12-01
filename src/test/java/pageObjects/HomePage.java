package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/a/span")
	WebElement lnkMyAccount;

	@FindBy(xpath = "//*[@id=\"top\"]/div/div[2]/ul/li[2]/div/ul/li[1]/a")
	WebElement lnkRegister;

	public void clickMyAccount() {
		lnkMyAccount.click();
	}

	public void clickRegister() {
		lnkRegister.click();
	}

}
