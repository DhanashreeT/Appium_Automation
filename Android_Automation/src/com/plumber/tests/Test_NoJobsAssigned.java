package com.plumber.tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.plumber.pages.HomePage;
import com.plumber.pages.PendingJobsPage;

/**
 * The Class Test_NoJobsAssigned.
 * @author Dhanashree
 */
public class Test_NoJobsAssigned extends BaseClass{
	HomePage objHomePage;
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
	 * Verifies pending jobs page when no jobs are assigned
	 */
	@Test(priority=1)
	public void verifyPendingJobWhenNoJobsAvailable() throws Exception {
		 objHomePage = new HomePage(driver);
		 objHomePage.clickPendingJobs();
		 objPendingJob = new PendingJobsPage(driver);
		 Assert.assertEquals("No new jobs are assigned.",  objPendingJob.getErrorMsg(),"Wrong Error msg is displayed");
		 objPendingJob.clickOnOk();
		 Assert.assertEquals("Pending Jobs", objHomePage.getTextOnHomePage(),"Plumber is not navigated to home page");
	}

	/**
	 * This method will quit driver after the execution of all test methods in this class 
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
