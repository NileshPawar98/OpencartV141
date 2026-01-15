package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {

	public AccountRegisterPage(WebDriver driver) {
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
	WebElement txtpassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement chkPolicy;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnContinue;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
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

	public void setTelephone(String phone) {
		txtTelephone.sendKeys(phone);
	}

	public void setpassword(String pass) {
		txtpassword.sendKeys(pass);
	}

	public void setConfirmpassword(String pass) {
		txtConfirmPassword.sendKeys(pass);
	}

	public void setPrivacyPolicy() {
		chkPolicy.click();
	}

	public void clickContinue() {
		//sol 1 
		btnContinue.click();
		//sol 2 
		//btnContinue.submit();
		
		//sol3
		//Actions ac = new Actions(driver);
		//ac.moveToElement(btnContinue).click().perform();
		
		//sol4
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", btnContinue);
	}
	
	public String getConfirmatioMsg() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
}
