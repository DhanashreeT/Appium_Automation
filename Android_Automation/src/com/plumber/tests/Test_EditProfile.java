package com.plumber.tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.plumber.pages.HomePage;
import com.plumber.pages.LoginPage;
import com.plumber.pages.ProfilePage;
import com.util.CSVDataProvider;
import com.util.DataProviderArguments;

/**
 * The Class Test_EditProfile.
 * 
 * @author Dhanashree
 */
public class Test_EditProfile extends BaseClass {

	LoginPage objLogin;
	HomePage objHomePage;
	ProfilePage profilePage;

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
	 * Verifies edit profile functionality
	 */
	@Test(dataProviderClass = CSVDataProvider.class, dataProvider = "CSVData")
	@DataProviderArguments(path = "dataFile/EditProfileInfo.csv")
	public void verifyEditProfile(String address, String number)
			throws Exception {
		objHomePage = new HomePage(driver);
		objHomePage.clickProfileMenu();
		objHomePage.clickViewProfile();
		profilePage = new ProfilePage(driver);
		profilePage.clickEditButton();
		profilePage.editDetails(address, number);
		Assert.assertEquals("Profile information saved.",
				profilePage.getSuccessMsg(), "Success message not displayed");
		profilePage.clickOk();
		profilePage.navigateBack();
		objHomePage.clickProfileMenu();
		objHomePage.clickViewProfile();
		System.out.println(profilePage.address.getText());
		System.out.println(profilePage.number.getText());
		Assert.assertEquals(address, profilePage.address.getText(),
				"Address did'nt match");
		Assert.assertEquals(number, profilePage.number.getText(),
				"Number did'nt match");
		profilePage.navigateBack();
	}

	/**
	 * This method will quit driver after the execution of all test methods in
	 * this class
	 */
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
