package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Class FeedbackPage.
 * 
 * @author Dhanashree Tannirwar
 */

public class FeedbackPage {

	AndroidDriver driver = null;

	@FindBy(xpath = "//android.widget.TextView[@text='Feedback']")
	WebElement titleText;

	@FindBy(id = "com.peninsula.plumber:id/secret_key")
	WebElement secretKey;

	@FindBy(id = "com.peninsula.plumber:id/note")
	WebElement notes;

	@FindBy(id = "com.peninsula.plumber:id/action_done")
	WebElement saveButton;

	@FindBy(xpath = "//android.widget.ImageButton[@index='0']")
	WebElement navigateBack;

	@FindBy(id = "android:id/message")
	WebElement errorMsg;

	@FindBy(id = "android:id/button1")
	WebElement okOnPopup;

	@FindBy(id = "com.peninsula.plumber:id/ratingBar")
	WebElement rating;

	/**
	 * Instantiates a new Feedback page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public FeedbackPage(AndroidDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Click on save button
	 */
	public void clickSave() {
		saveButton.click();
	}

	/**
	 * get title text
	 * 
	 * @return title text
	 */
	public String getTitleText() {
		return titleText.getText();
	}

	/**
	 * navigate back
	 */
	public void navigateBack() {
		navigateBack.click();
	}

	/**
	 * click on ok
	 */
	public void clickOnOk() {
		okOnPopup.click();
	}

	/**
	 * get the error message
	 * 
	 * @return error message
	 */
	public String getErrorMsg() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
		return errorMsg.getText();
	}

	/**
	 * add secret key and save
	 * 
	 * @param secretkey
	 */
	public void addSecretKey(String secretkey) {
		secretKey.sendKeys(secretkey);
		rating.click();
		saveButton.click();
	}
}
