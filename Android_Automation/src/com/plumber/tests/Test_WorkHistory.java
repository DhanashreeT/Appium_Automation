package com.plumber.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.plumber.data.Data_CompletedJobPage;
import com.plumber.pages.CompletedJobPage;
import com.plumber.pages.FeedbackPage;
import com.plumber.pages.HomePage;
import com.plumber.pages.LoginPage;
import com.plumber.pages.WorkHistory;

/**
 * The Class Test_WorkHistory.
 * @author Dhanashree
 */

public class Test_WorkHistory extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
	WorkHistory objWorkHistory;
	FeedbackPage objFeedbackPage;
	CompletedJobPage objCompletedJob;
	Properties prop = new Properties();
	InputStream input = null;

	/**
	 * Initializes the driver as per the capabilities
	 */
	@BeforeClass
	public void setup() throws MalformedURLException {
		try {
			initProcess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifies the work history for plumber
	 */
	@Test(priority = 1)
	public void verifyWorkHistoryForPlumber() throws Exception {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			String customerName = prop.getProperty("workHistory.customername");
			objHomePage = new HomePage(driver);
			objHomePage.clickWorkHistory();
			objWorkHistory = new WorkHistory(driver);
			objWorkHistory.clickOnCustomer(customerName);
			objCompletedJob = new CompletedJobPage(driver);
			Assert.assertEquals(Data_CompletedJobPage.completedJobPage_Title, objCompletedJob.getTitle(),
					"Plumber is not navigated to Completed job page ");
//			Assert.assertEquals("Completed Job", objCompletedJob.getTitle(),
//					"Plumber is not navigated to Completed job page ");
			Assert.assertEquals("TestFailure",
					objCompletedJob.getCustomerName(),
					"Work history is not displayed for mentioned customer");
			objCompletedJob.navigateBack();
			objFeedbackPage = new FeedbackPage(driver);
			objFeedbackPage.navigateBack();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method will quit driver after the execution of all test methods in this class 
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
