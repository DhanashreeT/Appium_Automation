package com.plumber.tests;

import java.io.IOException;
import java.net.MalformedURLException;

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
import com.util.CSVDataProvider;
import com.util.DataProviderArguments;

/**
 * The Class Test_BasicFlow.
 * @author Dhanashree
 */

public class Test_BasicFlow extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
	ProfilePage profilePage;
	PendingJobsPage objPendingJob;
	CurrentJobPage objCurrentJob;
	FeedbackPage objFeedbackPage;

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
	 * Verify the basic work flow of accepting , starting ,completing and giving feedback
	 */
	 
	@Test(dataProviderClass = CSVDataProvider.class, dataProvider = "CSVData")
	@DataProviderArguments(path = "dataFile/BasicflowData.csv")
	public void verifyBasicFlow(String CustomerName, String comment1,
			String comment2, String secretKey) throws IOException {
		try {

			System.out.println(CustomerName);
			System.out.println(comment1);
			System.out.println(comment2);
			System.out.println(secretKey);

			objHomePage = new HomePage(driver);
			objHomePage.clickPendingJobs();
			objPendingJob = new PendingJobsPage(driver);
			objPendingJob.clickOnCustomer(CustomerName);
			objPendingJob.clickAcceptJob();
			objCurrentJob = new CurrentJobPage(driver);
			Assert.assertTrue(objCurrentJob.isJobStarted(),
					"Current job not started");
			objCurrentJob.clickOnstartJob();
			//Thread.sleep(2000);
			objCurrentJob.clickAddComment();
			objCurrentJob.addCommentforCurrentJob(comment1);
			// Assert.assertEquals("Job started",
			// objCurrentJob.getAddedCommentText());
			objCurrentJob.clickOnPauseJob();
			objCurrentJob.addCommentforCurrentJob(comment2);
			//Thread.sleep(2000);
			objCurrentJob.clickOnResumeJob();
			//Thread.sleep(2000);
			objCurrentJob.clickOnFinishJob();
			objCurrentJob.clickOnOk();
			objFeedbackPage = new FeedbackPage(driver);
			Assert.assertEquals("Feedback", objFeedbackPage.getTitleText(),
					"Job is not completed as plumber is not navigated to feedback page");
			objFeedbackPage.addSecretKey(secretKey);
			//Thread.sleep(2000);
			Assert.assertEquals("Pending Jobs",
					objHomePage.getTextOnHomePage(), "Feedback not saved");
		} catch (Exception ex) {
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
