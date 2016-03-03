package com.plumber.tests;

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

/**
 * The Class Test_currentJob.
 * @author Dhanashree
 */

public class Test_currentJob extends BaseClass{

	LoginPage objLogin;
	HomePage objHomePage;
	ProfilePage profilePage;
	CurrentJobPage objCurrentJob;
	FeedbackPage objFeedbackPage;
	PendingJobsPage objPendingJob;
	
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
	 * Verify whether plumber is able to complete current job
	 */
	@Test(priority=1)
	public void verifyCompletingCurrentJob() throws Exception {
		 objHomePage = new HomePage(driver);
		 objHomePage.clickCurrentJob();
		 objCurrentJob = new CurrentJobPage(driver);
		// objCurrentJob.clickOnstartJob();
		 objCurrentJob.clickAddComment();
		 objCurrentJob.addCommentforCurrentJob("Job started");
		// Assert.assertEquals("Job started", objCurrentJob.getAddedCommentText());
		 objCurrentJob.clickOnPauseJob();
		 objCurrentJob.addCommentforCurrentJob("work Paused");
		 objCurrentJob.clickOnResumeJob();
		 objCurrentJob.clickOnFinishJob();
		 objCurrentJob.clickOnOk();
		 objFeedbackPage = new FeedbackPage(driver);
		 Assert.assertEquals("Feedback", objFeedbackPage.getTitleText(),"Job is not completed as Plumber is not navigated to Feedback page");
		 objFeedbackPage.navigateBack();     
	}
	
	/**
	 * Checks current job tab for completed job
	 */
	@Test(priority=2)
	public void verifyCurrentJobForCompletedJob() throws Exception {
		 objHomePage = new HomePage(driver);
		 objHomePage.clickCurrentJob();
		 objCurrentJob = new CurrentJobPage(driver);
		 Assert.assertTrue((objCurrentJob.getErrorMsg()).contains("Current job is already completed"));
		 objCurrentJob.clickOnOk();
		 objFeedbackPage = new FeedbackPage(driver);
		 Assert.assertEquals("Feedback", objFeedbackPage.getTitleText(),"Plumber is not navigated to feedback page");
	}
	
	/**
	 * This method will quit driver after the execution of all test methods in this class 
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
