package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	WebDriver driver;

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkPrivacyPolicy;

	@FindBy(xpath = "//button[normalize-space()='Continue']")
	WebElement btnContinue;

	@FindBy(xpath = "//button[normalize-space()='your Account Has Been Created!']")
	WebElement msgConfirmation;

	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTelephone(String telephone) {
		txtTelephone.sendKeys(telephone);
	}

	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}

	public void setConfirmPassword(String password) {
		txtConfirmPassword.sendKeys(password);
	}

	public void checkPrivacyPolicy() {
		chkPrivacyPolicy.click();
	}

	public void clickContinue() {
		// sol 1
		btnContinue.click();

		// sol 2: javascript Executor
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].click();", btnContinue);

		// sol 3: Action
		// Actions actions = new Actions(driver);
		// actions.moveToElement(btnContinue).click().perform();

		// sol 4: Submit
		// btnContinue.submit();

		// sol 5: Using Keys
		// btnContinue.sendKeys(Keys.ENTER);

		// sol 6: Using wait
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10
		// wait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

	}

	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
