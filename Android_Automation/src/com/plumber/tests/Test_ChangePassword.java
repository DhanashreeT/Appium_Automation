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

import com.plumber.pages.HomePage;
import com.plumber.pages.LoginPage;
import com.plumber.pages.ProfilePage;

/**
 * The Class Test_ChangePassword.
 * @author Dhanashree
 */

public class Test_ChangePassword extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
	ProfilePage profilePage;
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
	 * Verifies the change password functionality
	 */
	@Test
	public void verifyChangedPassword() throws Exception {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objLogin = new LoginPage(driver);
			String username = prop.getProperty("punePlumber.username");
			String password = prop.getProperty("punePlumber.password");
			String newPassword = prop.getProperty("punePlumber.newPassword");
			objHomePage = new HomePage(driver);
			objHomePage.clickProfileMenu();
			objHomePage.clickViewProfile();
			profilePage = new ProfilePage(driver);
			profilePage.clickEditButton();
			profilePage.clickChangePassword();
			profilePage.changePassword(password, newPassword, newPassword);
			Assert.assertEquals("Password changed successfully.",
					profilePage.getSuccessMsg(),
					"Success message not displayed correctly");
			profilePage.clickOk();
			profilePage.navigateBack();
			objHomePage.clickProfileMenu();
			objHomePage.clickSignOut();
			objLogin = new LoginPage(driver);
			objLogin.login(username, newPassword);
			Assert.assertEquals("Pending Jobs",
					objHomePage.getTextOnHomePage(), "Login not successful");
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
