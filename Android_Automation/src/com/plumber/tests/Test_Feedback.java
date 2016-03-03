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
 * The Class Test_Feedback.
 * @author Dhanashree
 */
public class Test_Feedback extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
	ProfilePage profilePage;
	CurrentJobPage objCurrentJob;
	FeedbackPage objFeedbackPage;
	PendingJobsPage objPendingJob;
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

//	/**
//	 * Verify whether plumber is able to accept another job before giving feedback for previous job
//	 */ 
//	@Test(priority = 1)
//	public void verifyAcceptJobBeforeFeedbackToPrevious() throws Exception {
//		objHomePage = new HomePage(driver);
//		objHomePage.clickPendingJobs();
//		objPendingJob = new PendingJobsPage(driver);
//		objPendingJob.clickOnJob();
//		objPendingJob.clickAcceptJob();
//		Assert.assertEquals("Please finish current job.",
//				objPendingJob.getErrorMsg(), "Wrong error message is displayed");
//		objPendingJob.clickOnOk();
//		objCurrentJob = new CurrentJobPage(driver);
//		objCurrentJob.clickOnOk();
//		objFeedbackPage = new FeedbackPage(driver);
//		Assert.assertEquals("Feedback", objFeedbackPage.getTitleText(),
//				"Job is accepted as User is not navigated to Feedback page");
//		objFeedbackPage.navigateBack();
//		objPendingJob.navigateBack();
//	}

	/**
	 *  Check whether feedback can be submitted with invalid secret key
	 */
	@Test(priority = 2)
	public void verifyFeedbackWithInvalidSecretKey() throws Exception {
		objHomePage = new HomePage(driver);
		objHomePage.clickCurrentJob();
		objCurrentJob = new CurrentJobPage(driver);
		Assert.assertTrue((objCurrentJob.getErrorMsg())
				.contains("Current job is already completed"));
		objCurrentJob.clickOnOk();
		objFeedbackPage = new FeedbackPage(driver);
		Assert.assertEquals("Feedback", objFeedbackPage.getTitleText(),
				"Plumber is not navigated to feedback page");
		objFeedbackPage.addSecretKey(prop
				.getProperty("feedbackPage.wrongsecretKey"));
		Assert.assertTrue((objFeedbackPage.getErrorMsg())
				.contains("Secret key is wrong"));
		objFeedbackPage.clickOnOk();
		objFeedbackPage.navigateBack();
	}

	/**
	 *  Check whether feedback can be submitted with valid secret key
	 */
	@Test(priority = 3)
	public void verifyFeedbackWithValidSecretKey() throws Exception {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objHomePage = new HomePage(driver);
			objHomePage.clickCurrentJob();
			objCurrentJob = new CurrentJobPage(driver);
			Assert.assertTrue((objCurrentJob.getErrorMsg())
					.contains("Current job is already completed"));
			objCurrentJob.clickOnOk();
			objFeedbackPage = new FeedbackPage(driver);
			Assert.assertEquals("Feedback", objFeedbackPage.getTitleText(),
					"Plumber is not navigated to feedback page");
			objFeedbackPage.addSecretKey(prop
					.getProperty("feedbackPage.secretKey"));
			Assert.assertEquals("Pending Jobs",
					objHomePage.getTextOnHomePage(), "Feedback not saved");
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
