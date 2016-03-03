package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The Class HomePage.
 * 
 * @author Dhanashree
 */

public class HomePage {
	AndroidDriver driver = null;

	@FindBy(xpath = "//android.widget.TextView[@text='Pending Jobs']")
	WebElement TextOnHomePage;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	WebElement profileMenu;

	@FindBy(id = "com.peninsula.plumber:id/job_count")
	WebElement pendingJobCount;

	@FindBy(id = "com.peninsula.plumber:id/assign_job_layout")
	WebElement pendingJobs;

	@FindBy(id = "com.peninsula.plumber:id/history_layout")
	WebElement workHistory;

	@FindBy(id = "com.peninsula.plumber:id/current_work_layout")
	WebElement currentJob;

	@FindBy(id = "com.peninsula.plumber:id/profile_photo")
	WebElement profilePhoto;

	@FindBy(xpath = "//android.widget.TextView[@text='View Profile']")
	WebElement viewProfile;

	@FindBy(xpath = "//android.widget.TextView[@text='Sign Out']")
	WebElement signOut;

	/**
	 * Instantiates a new Home page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public HomePage(AndroidDriver driver) {

		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Get the User name from Home Page
	 * 
	 * @return title text
	 */
	public String getTextOnHomePage() {
		return TextOnHomePage.getText();
	}

	/**
	 * Get pending job count
	 * 
	 * @return job count
	 */
	public String getpendingJobCount() {
		return pendingJobCount.getText();
	}

	/**
	 * Click on pending jobs tab
	 * 
	 * @throws InterruptedException
	 */
	public void clickPendingJobs() throws InterruptedException {
		pendingJobs.click();
	//	Thread.sleep(2000);
	}

	/**
	 * Click on work history tab
	 */
	public void clickWorkHistory() {
		workHistory.click();
	}

	/**
	 * Click on Current Job
	 */
	public void clickCurrentJob() {
		currentJob.click();
	}

	/**
	 * Check whether current jobs tab present
	 * 
	 * @return boolean value
	 */
	public boolean isCurrentJobTabPresent() {
		return currentJob.isDisplayed();
	}

	/**
	 * Click on three dots button
	 */
	public void clickProfileMenu() {
		profileMenu.click();
	}

	/**
	 * Click on three dots button
	 */
	public void clickProfilePhoto() {
		profilePhoto.click();
	}

	/**
	 * Click on signout button
	 */
	public void clickSignOut() {
		signOut.click();
	}

	/**
	 * Click on view profile
	 */
	public void clickViewProfile() {
		viewProfile.click();
	}

}
