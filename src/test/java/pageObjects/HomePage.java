package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	
	public HomePage(WebDriver driver)   //Constructor
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[@title='My Account']")   // Locators
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement lnkRegister;
	
	@FindBy(linkText = "Login")			// login link added in step 5
	WebElement linklogin;
	
	public void clickMyaccount()			//Action methods
	{
		lnkMyaccount.click();
	}
	
	public void clickRagister()
	{
		lnkRegister.click();
	}
	public void Clicklogin()
	{
		linklogin.click();
	}
}
