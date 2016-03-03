package com.plumber.tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.plumber.pages.HomePage;
import com.plumber.pages.LoginPage;
import com.plumber.pages.PendingJobsPage;

/**
 * The Class Test_JobCount.
 * @author Dhanashree
 */
public class Test_JobCount extends BaseClass{
	LoginPage objLogin;
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
	 * Verifies job count
	 */
	@Test(priority=1)
	public void verifyJobCount() throws Exception {
		 objHomePage = new HomePage(driver);
		 String JobCount =objHomePage.getpendingJobCount();
         objHomePage.clickPendingJobs();
         objPendingJob = new PendingJobsPage(driver);
         Assert.assertEquals(JobCount, String.valueOf(objPendingJob.getJobsSize()),"Job count does not match");
	}

	/**
	 * This method will quit driver after the execution of all test methods in this class 
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
