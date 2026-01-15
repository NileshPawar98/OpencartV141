package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

							//Base Class is Parent class of all class
public class BaseClass {   //We need to create this class for methods which is used multiple times or commonly used 
	
public static WebDriver driver;
public Logger logger; //log4j
public Properties p;
	

	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		// loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());    // log4j2 code
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))    // if Execution Env is remote
		{
			DesiredCapabilities capabilites = new DesiredCapabilities();
			
			//os
	/*		if(os.equalsIgnoreCase("windows")) {
				capabilites.setPlatform(Platform.WINDOWS);
				
			}
			else if(os.equalsIgnoreCase("linux"))
				
			{
				capabilites.setPlatform(Platform.LINUX);
				
			}
			else if(os.equalsIgnoreCase("mac"))
				
			{
				capabilites.setPlatform(Platform.MAC);
				
			}
			else
			{
				System.out.println("no macthing os");
				return;
			}  */
			//browser
			switch(br.toLowerCase())
			{
			case "chrome" : capabilites.setBrowserName("chrome") ; break;
			case "edge" : capabilites.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : capabilites.setBrowserName("firefox");break;
			default : System.out.println("No matching browser.."); return;
			}
		//	driver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), capabilites);
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilites);   // launching chrome driver
		//	driver=new RemoteWebDriver(new URL("http://localhost:4444"),capabilites);   // launching chrome driver
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))   // if Execution environment is local 
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver= new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("appURL"));  // we are taking this from properties file
		
		driver.manage().window().maximize();
	}
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void teardown() throws InterruptedException
	{
		//Thread.sleep(2000);   //we have added thread.sleep to avoid warning msg in console
		driver.quit();;
	}
	public String ramdomString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumber()
	{
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}
	public String randomAlphaNumberic()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNumber = RandomStringUtils.randomNumeric(3);
		return (generatedString+"@"+generatedNumber);
	}
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
