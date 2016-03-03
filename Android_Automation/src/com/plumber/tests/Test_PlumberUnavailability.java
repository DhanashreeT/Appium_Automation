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

import com.plumber.pages.CurrentJobPage;
import com.plumber.pages.FeedbackPage;
import com.plumber.pages.HomePage;
import com.plumber.pages.LoginPage;
import com.plumber.pages.PendingJobsPage;
import com.plumber.pages.ProfilePage;

/**
 * The Class Test_PlumberUnavailability.
 * @author Dhanashree
 */

public class Test_PlumberUnavailability extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
	ProfilePage profilePage;
	PendingJobsPage objPendingJob;
	CurrentJobPage objCurrentJob;
	FeedbackPage objFeedbackPage;
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
	 *  Check whether plumber is able to start the job when his status is unavailable
	 */
	@Test(priority = 1)
	public void verifyStartJobOnPlumberUnavailability() throws Exception {

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objHomePage = new HomePage(driver);
			objHomePage.clickProfileMenu();
			objHomePage.clickViewProfile();
			profilePage = new ProfilePage(driver);
			profilePage.clickOnAvailibilitySwitch();
			profilePage.navigateBack();
			objHomePage.clickPendingJobs();
			objPendingJob = new PendingJobsPage(driver);
			objPendingJob.clickOnCustomer(prop
					.getProperty("plumberUnavailability.customerName"));
			objPendingJob.clickAcceptJob();
			objCurrentJob = new CurrentJobPage(driver);
			// objHomePage.clickCurrentJob();
			objCurrentJob.clickOnstartJob();
			Assert.assertEquals("Your current status is Unavailable.",
					objCurrentJob.getErrorMsg());
			objCurrentJob.clickOnOk();
			profilePage.clickOnAvailibilitySwitch();
			profilePage.navigateBack();
			objCurrentJob.clickOnstartJob();
			Assert.assertEquals("Pause work", objCurrentJob.getPauseWorkText(),
					"Job not started");
			objCurrentJob.navigateBack();
			objPendingJob.navigateBack();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 *  Check whether plumber is able to finish the job when his status is unavailable
	 */
	@Test(priority = 2)
	public void verifyFinishJobOnPlumberUnavailability() throws Exception {

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		objHomePage = new HomePage(driver);
		objHomePage.clickProfileMenu();
		objHomePage.clickViewProfile();
		profilePage = new ProfilePage(driver);
		profilePage.clickOnAvailibilitySwitch();
		profilePage.navigateBack();
		objHomePage.clickCurrentJob();
		objCurrentJob = new CurrentJobPage(driver);
		objCurrentJob.clickOnFinishJob();
		//Thread.sleep(2000);
		Assert.assertEquals("Your current status is Unavailable.",
				objCurrentJob.getErrorMsg());
		objCurrentJob.clickOnOk();
		profilePage.clickOnAvailibilitySwitch();
		profilePage.navigateBack();
		objCurrentJob.clickOnFinishJob();
		Assert.assertTrue((objCurrentJob.getErrorMsg())
				.contains("Please fill customer feedback."),
				"Wrong Error message is displayed ");
		objCurrentJob.clickOnOk();
		objFeedbackPage = new FeedbackPage(driver);
		Assert.assertEquals("Feedback", objFeedbackPage.getTitleText(),
				"Job is not completed as plumber is not navigated to feedback page");
		objFeedbackPage.addSecretKey(prop
				.getProperty("plumberUnavailability.secretKey"));
		//Thread.sleep(2000);
		Assert.assertEquals("Pending Jobs", objHomePage.getTextOnHomePage(),
				"Feedback not saved");
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
