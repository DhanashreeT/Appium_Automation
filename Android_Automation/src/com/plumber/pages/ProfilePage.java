package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Class ProfilePage.
 * 
 * @author Dhanashree
 */
public class ProfilePage {
	AndroidDriver driver = null;

	@FindBy(id = "com.peninsula.plumber:id/action_edit")
	WebElement editButton;

	@FindBy(xpath = "//android.widget.ToggleButton[@index='1']")
	WebElement changeAvailability;

	@FindBy(id = "com.peninsula.plumber:id/address_editText")
	public WebElement address;

	@FindBy(id = "com.peninsula.plumber:id/mobile_number_editText")
	public WebElement number;

	@FindBy(id = "android:id/message")
	WebElement successmsg;

	@FindBy(id = "android:id/button1")
	WebElement okOnPopup;

	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
	WebElement navigateBack;

	@FindBy(id = "com.peninsula.plumber:id/action_done")
	WebElement saveButton;

	@FindBy(xpath = "//android.widget.EditText[@text='Change Password?']")
	WebElement changePassword;

	@FindBy(id = "com.peninsula.plumber:id/old_password")
	WebElement oldPassword;

	@FindBy(id = "com.peninsula.plumber:id/new_password")
	WebElement newPassword;

	@FindBy(id = "com.peninsula.plumber:id/new_reconfirm_password")
	WebElement confirmNewPassword;

	/**
	 * Instantiates a new ProfilePage.
	 * 
	 * @param driver
	 *            the driver
	 */
	public ProfilePage(AndroidDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Click on edit button
	 */
	public void clickEditButton() {
		editButton.click();
	}

	/**
	 * Click on save button
	 */
	public void clickProfileMenu() {
		saveButton.click();
	}

	/**
	 * Click on change password
	 */
	public void clickChangePassword() {
		changePassword.click();
		changePassword.click();
	}

	/**
	 * get success message
	 * 
	 * @return success message
	 */
	public String getSuccessMsg() {
		return successmsg.getText();
	}

	/**
	 * CLick On Ok
	 */
	public void clickOk() {
		okOnPopup.click();
	}

	/**
	 * navigate back
	 */
	public void navigateBack() {
		navigateBack.click();
	}

	/**
	 * Click on availability button
	 */
	public void clickOnAvailibilitySwitch() {
		changeAvailability.click();
	}

	/**
	 * Saves the edited profile details
	 * 
	 * @param editAddress
	 * @param mobileNumber
	 * @throws InterruptedException
	 */
	public void editDetails(String editAddress, String mobileNumber)
			throws InterruptedException {
		number.clear();
		number.sendKeys(mobileNumber);
		address.clear();
		address.sendKeys(editAddress);
		saveButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
		//Thread.sleep(3000);
	}

	/**
	 * Saves the changed password
	 * 
	 * @param oldpwd
	 * @param newpwd
	 * @param cnfmpwd
	 * @throws InterruptedException
	 */
	public void changePassword(String oldpwd, String newpwd, String cnfmpwd)
			throws InterruptedException {
		confirmNewPassword.click();
		confirmNewPassword.sendKeys(cnfmpwd);
		driver.navigate().back();
		// driver.sendKeyEvent(AndroidKeyCode.ENTER);
		oldPassword.sendKeys(oldpwd);
		// driver.sendKeyEvent(AndroidKeyCode.ENTER);
		newPassword.sendKeys(newpwd);
		saveButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
		//Thread.sleep(3000);
	}
}
