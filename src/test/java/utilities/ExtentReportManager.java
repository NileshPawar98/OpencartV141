package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
	/*	SimpleDateFormat df = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss");	//Date timeStamp
		Date dt=new Date();
		String currentdatetimestap=df.format(dt);
	*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  // time Stamp
		
		repName = "Test-Report-"+ timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);  // specify location of the report
		
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");  // Title of report 
		sparkReporter.config().setReportName("Opencart Function Testing");   	// name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Moduel", "Admin");
		extent.setSystemInfo("Sub Moduel", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");  //Capturing Os name from xml file
		extent.setSystemInfo("Operating system", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");//Capturing browser name from xml file
		extent.setSystemInfo("Browser", browser);
		
		List<String>includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();  //Capturing groups from xml file(includegroups)
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());   // to display groups in report
		test.log(Status.PASS, result.getName()+ " got successfully executed");
	}
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
		String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}  
	public void onTestSkiped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;  // report will automatically open once it generated
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//As soon report is generated you need to sent report automatically on email 
/*		try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			// Create the Email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("pavanoltraining@gmail.com");  //Sender
			email.setSubject("Test Result");
			email.setMsg("Please find attached report....");
			email.addTo("pavankumar.busyqa@gmail.com");  //Receiver
			email.attach(url, "extent report", "please check report...");
			email.send();  // sent the email
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
*/		
	}
			
}
