package com.plumber.tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.plumber.pages.HomePage;
import com.plumber.pages.LoginPage;

/**
 * The Class Test_Logout.
 * @author Dhanashree
 */
public class Test_Logout extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;

	
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
	 * Checks logout functionality
	 */
	@Test
	public void logoutTest() throws Exception {
		objHomePage = new HomePage(driver);
		objHomePage.clickProfileMenu();
		objHomePage.clickSignOut();
		objLogin = new LoginPage(driver);
		Assert.assertEquals("Login", objLogin.getLoginButtonText(),
				"Logout not successful");
	}

	/**
	 * This method will quit driver after the execution of all test methods in this class 
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
