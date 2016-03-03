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

/**
 * The Class Test_Login.
 * @author Dhanashree
 */
public class Test_Login extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
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
	 * Checks login functionality
	 */
	@Test
	public void loginTest() throws Exception {
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			objLogin = new LoginPage(driver);
			String username = prop.getProperty("punePlumber.username");
			String password = prop.getProperty("punePlumber.password");
			objLogin.login(username, password);
			// Lands to home page
			objHomePage = new HomePage(driver);
			System.out.println(objHomePage.getTextOnHomePage());
			// Verify home page
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
