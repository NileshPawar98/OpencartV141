package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("**** Starting TC001_AccountRegistrationTest *****");
		
		try   // if find any exception then try catch block will be Executed 
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		logger.info("Clicked on MyAccount Link ");
		
		hp.clickRagister();
		logger.info("Clicked on Reister Link ");
		
		AccountRegisterPage regpage = new AccountRegisterPage(driver);
		
		logger.info("Providing customer Details...");
		regpage.setFirstName(ramdomString().toUpperCase());  //Random name 
		regpage.setLastName(ramdomString().toUpperCase()); //Random Last name
		regpage.setEmail(ramdomString()+"@gmail.com");   //Random Email
		regpage.setTelephone(randomNumber());
		
		String password = randomAlphaNumberic();
		
		regpage.setpassword(password);
		regpage.setConfirmpassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		
		logger.info("Validating Expected message...");
		String confmsg=regpage.getConfirmatioMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test failed..");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest ******");
		
	}
	
	
}
