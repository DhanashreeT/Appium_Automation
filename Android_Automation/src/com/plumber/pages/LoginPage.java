package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The Class LoginPage.
 * 
 * @author Dhanashree
 */
public class LoginPage {
	AndroidDriver driver = null;

	@FindBy(id = "com.peninsula.plumber:id/username_EditText")
	WebElement userName;

	@FindBy(id = "com.peninsula.plumber:id/password_EditText")
	WebElement password;

	@FindBy(id = "com.peninsula.plumber:id/login_Button")
	WebElement login;

	/**
	 * Instantiates a new Login page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Set user name in textbox
	 * 
	 * @param strUserName
	 */

	public void setUserName(String strUserName) {
		userName.clear();
		userName.sendKeys(strUserName);
	}

	/**
	 * Set password in password textbox
	 * 
	 * @param strPassword
	 */

	public void setPassword(String strPassword) {
		password.clear();
		password.sendKeys(strPassword);
	}

	/**
	 * Click on login button
	 */
	public void clickLogin() {
		login.click();
	}

	/**
	 * Click on login button
	 * 
	 * @return login button text
	 */
	public String getLoginButtonText() {
		return login.getText();
	}

	/**
	 * 
	 * This POM method will be exposed in test case to login in the application
	 * 
	 * @param strUserName
	 * 
	 * @param strPasword
	 * 
	 * @return
	 * @throws InterruptedException
	 */

	public void login(String strUserName, String strPasword)
			throws InterruptedException {

		// Fill user name
		this.setUserName(strUserName);
		// Fill password
		this.setPassword(strPasword);
		// Click Login button
		this.clickLogin();
		//Thread.sleep(1000);
	}
}
