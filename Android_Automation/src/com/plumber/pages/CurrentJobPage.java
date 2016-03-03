package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Class CurrentJobPage.
 * @author Dhanashree
 */
public class CurrentJobPage {

	AndroidDriver driver = null;

	@FindBy(xpath = "//android.widget.TextView[@text='Current Job']")
	WebElement titleText;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.peninsula.plumber:id/action_button']")
	WebElement startButton;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.peninsula.plumber:id/action_button']")
	WebElement pauseButton;

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='com.peninsula.plumber:id/action_button']")
	WebElement resumeButton;

	@FindBy(id = "com.peninsula.plumber:id/button_text")
	WebElement pauseWork;

	@FindBy(id = "com.peninsula.plumber:id/add_comment")
	WebElement addComment;

	@FindBy(id = "com.peninsula.plumber:id/editTextDialogUserInput")
	WebElement inputComment;

	@FindBy(id = "com.peninsula.plumber:id/comment_text")
	WebElement commentedText;

	@FindBy(id = "com.peninsula.plumber:id/finish_layout")
	WebElement finishButton;

	@FindBy(xpath = "//android.widget.ImageButton[@index='0']")
	WebElement navigateBack;

	@FindBy(id = "android:id/message")
	WebElement errorMsgOfUnavailability;

	@FindBy(id = "android:id/button1")
	WebElement okOnPopup;

	/**
	 * Instantiates a new current job page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public CurrentJobPage(AndroidDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * get the title of page
	 * 
	 * @return title text
	 */
	public String getTitle() {
		return titleText.getText();
	}

	/**
	 * Check whether current job started
	 * 
	 * @return boolean value
	 */
	public boolean isJobStarted() {
		return startButton.isDisplayed();
	}

	/**
	 * Click on start job
	 */
	public void clickOnstartJob() {
		startButton.click();
	}

	/**
	 * Click on pause job
	 */
	public void clickOnPauseJob() {
		pauseButton.click();
	}

	/**
	 * Click on finish job
	 */
	public void clickOnFinishJob() {
		finishButton.click();
	}

	/**
	 * Click on resume job
	 */
	public void clickOnResumeJob() {
		resumeButton.click();
	}

	/**
	 * get text for pause work
	 * 
	 * @return pause work text
	 */
	public String getPauseWorkText() {
		return pauseWork.getText();
	}

	/**
	 * get commented text
	 * 
	 * @return commented text
	 */
	public String getAddedCommentText() {
		return commentedText.getText();
	}

	/**
	 * Click on add comment
	 */
	public void clickAddComment() {
		addComment.click();
	}

	/**
	 * Add comment and save
	 * 
	 * @param comment
	 */
	public void addCommentforCurrentJob(String comment) {
		inputComment.sendKeys(comment);
		okOnPopup.click();
	}

	/**
	 * Navigate back
	 */
	public void navigateBack() {
		navigateBack.click();
	}

	/**
	 * get error msg for unavailibility of plumber
	 * 
	 * @return error message
	 */
	public String getErrorMsg() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
		return errorMsgOfUnavailability.getText();
	}

	/**
	 * Click ok on popup
	 */
	public void clickOnOk() {
		okOnPopup.click();
	}
}
