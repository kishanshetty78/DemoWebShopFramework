package com.tricentis.demowebshop.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tricentis.demowebshop.utils.TestReportFormat;
import com.tricentis.demowebshop.utils.Utilities;

public class MyListeners implements ITestListener{

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testname;
	
	@Override
	public void onStart(ITestContext context) {
		extentReport = TestReportFormat.generateTestReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testname = result.getName();
		extentTest = extentReport.createTest(testname);
		extentTest.log(Status.INFO, testname+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, testname+" got executed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		// Getting the driver here
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String destnScreenshot = Utilities.captureScreenshot(driver, testname);
		
		extentTest.addScreenCaptureFromPath(destnScreenshot);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testname+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname+" got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathofExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathofExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
