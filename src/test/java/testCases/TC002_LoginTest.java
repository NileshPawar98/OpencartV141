package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;

public class TC002_LoginTest extends BaseClass {

	
	@Test(groups = {"Sanity","Master"})
	public void verify_Login() {
		logger.info("****** Starting TC002_LoginTest *****");
		System.out.println("Entered into Verfiy_Login Method");
		try {
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.Clicklogin();
		
		//login page
		loginPage lp = new loginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clicklogin();
		
		//myAccount
		myAccountPage macc = new myAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetPage);  //Assert.assertEquals(targetPage, true, "Login failed");
		
		System.out.println("Entered into Verfiy_Login Executed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished TC002_LoginTest *****");
		
		}
	}


