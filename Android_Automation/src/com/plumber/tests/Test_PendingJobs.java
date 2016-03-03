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
import com.plumber.pages.HomePage;
import com.plumber.pages.LoginPage;
import com.plumber.pages.PendingJobsPage;
import com.plumber.pages.ProfilePage;

/**
 * The Class Test_PendingJobs.
 * @author Dhanashree
 */
public class Test_PendingJobs extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
	ProfilePage profilePage;
	PendingJobsPage objPendingJob;
	CurrentJobPage objCurrentJob;
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
	 *  Verify whether plumber able to skip normal jobs
	 */
	@Test(priority = 1)
	public void verifySkipingNormalJob() throws Exception {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objHomePage = new HomePage(driver);
			objHomePage.clickPendingJobs();
			objPendingJob = new PendingJobsPage(driver);
			objPendingJob.clickOnCustomer(prop
					.getProperty("pendingJob.normalCustomer"));
			objPendingJob.clickSkipJob();
			System.out.println(objPendingJob.getTitle());
			Assert.assertEquals("Pending Jobs", objPendingJob.getTitle(),
					"Plumber is not able to skip normal job");
			objPendingJob.navigateBack();
			objPendingJob.navigateBack();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 *  Verify whether plumber able to skip priority jobs
	 */
	@Test(priority = 2)
	public void verifySkipingPriorityJob() throws Exception {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objHomePage = new HomePage(driver);
			objHomePage.clickPendingJobs();
			objPendingJob = new PendingJobsPage(driver);
			objPendingJob.clickOnCustomer(prop
					.getProperty("pendingJob.proirityCustomer"));
			Assert.assertFalse(objPendingJob.isSkipClickable(),
					"Skip is enabled for priority jobs");
			objPendingJob.navigateBack();
			objPendingJob.navigateBack();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 *  Verify whether plumber is able to accept job
	 */
	@Test(priority = 3)
	public void verifyAcceptingJob() throws Exception {

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objHomePage = new HomePage(driver);
			objPendingJob = new PendingJobsPage(driver);
			objHomePage.clickPendingJobs();
			// objPendingJob.clickOnJob();
			objPendingJob.clickOnCustomer(prop
					.getProperty("pendingJob.normalCustomer"));
			objPendingJob.clickAcceptJob();
			objCurrentJob = new CurrentJobPage(driver);
			Assert.assertTrue(objCurrentJob.isJobStarted(),
					"Current job not started");
			objCurrentJob.navigateBack();
			objPendingJob.navigateBack();
			Assert.assertTrue(objHomePage.isCurrentJobTabPresent(),
					"Current job tab is not present");
			objHomePage.clickCurrentJob();
			objCurrentJob.clickOnstartJob();
			objCurrentJob.navigateBack();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Verify whether plumber is able to accept another job before finishing previous job
	 */
	 
	@Test(priority = 4)
	public void verifyAcceptJobBeforeCompletingPrevious() throws Exception {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objHomePage = new HomePage(driver);
			objPendingJob = new PendingJobsPage(driver);
			objHomePage.clickPendingJobs();
			// objPendingJob.clickOnJob();
			objPendingJob.clickOnCustomer(prop
					.getProperty("pendingJob.proirityCustomer"));
			objPendingJob.clickAcceptJob();
			Assert.assertEquals("Please finish current job.",
					objPendingJob.getErrorMsg(),
					"Wrong error message is displayed");
			objPendingJob.clickOnOk();
			objCurrentJob = new CurrentJobPage(driver);
			Assert.assertEquals("Current Job", objCurrentJob.getTitle(),
					"Job is accepted as User is not navigated to current job page");
			objPendingJob.navigateBack();
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
