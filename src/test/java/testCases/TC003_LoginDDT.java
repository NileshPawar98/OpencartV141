package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import utilities.DataProviders;

/* Data is valid - login success - test pass - logout
  Data is valid - login failed - test fail
 
  Data is invalid - login success - test fail  - logout
  Data is invalid - login failed - test pass
 */
public class TC003_LoginDDT extends BaseClass {

	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="DataDriven") // getting data provider from different class
	public void verfiy_loginDDT(String email, String pwd, String exp) throws InterruptedException {

		logger.info("****** Starting TC003_LoginDDT ******");
		
		try {
		// HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.Clicklogin();

		// login page
		loginPage lp = new loginPage(driver);
		lp.setEmailAddress(email);
		lp.setPassword(pwd);
		lp.clicklogin();

		// myAccount
		myAccountPage macc = new myAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();

		if (exp.equalsIgnoreCase("Valid")) 
		{
			if (targetPage == true) 
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		if (exp.equalsIgnoreCase("Invalid")) 
		{
			if (targetPage == true) 
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			} 
			else 
			{
				Assert.assertTrue(true);
			}
		}
		
		}
		catch(Exception e)
		{
			logger.error("Logout Failed", e);
			Assert.fail();
		}
		Thread.sleep(3000);
		
		logger.info("****** Finished TC003_LoginDDT ******");
	}

}
