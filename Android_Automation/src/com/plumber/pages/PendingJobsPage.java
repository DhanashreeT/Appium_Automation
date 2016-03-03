package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The Class PendingJobsPage.
 * 
 * @author Dhanashree
 */
public class PendingJobsPage {
	AndroidDriver driver = null;

	@FindBy(xpath = "//android.widget.TextView[@text='Pending Jobs']")
	WebElement pendingJobsHeading;

	@FindBy(id = "com.peninsula.plumber:id/customer_name")
	public WebElement firstJob;

	@FindBy(id = "com.peninsula.plumber:id/skip_layout")
	public WebElement skipJob;

	@FindBy(xpath = "//android.widget.ImageButton[@index='0']")
	public WebElement navigateBack;

	@FindBy(id = "com.peninsula.plumber:id/accept_layout")
	public WebElement acceptJob;

	@FindBy(id = "android:id/message")
	public WebElement errorMsg;

	@FindBy(id = "android:id/button1")
	public WebElement okOnPopup;

	@FindBy(id = "com.peninsula.plumber:id/customer_name")
	public WebElement customerName;

	@FindAll({ @FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.peninsula.plumber:id/job_recycle_view']/android.widget.LinearLayout") })
	public List<WebElement> totalJobs;

	/**
	 * Instantiates a new PendingJobsPage.
	 * 
	 * @param driver
	 *            the driver
	 */
	public PendingJobsPage(AndroidDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Click on skip job
	 */
	public void clickSkipJob() {
		skipJob.click();
	}

	/**
	 * Click on skip job
	 * 
	 * @return boolean value
	 */
	public boolean isSkipClickable() {
		return skipJob.isEnabled();
	}

	/**
	 * Click on Accept job
	 */
	public void clickAcceptJob() {
		acceptJob.click();
	}

	/**
	 * Click on ok on popup
	 */
	public void clickOnOk() {
		okOnPopup.click();
	}

	/**
	 * get size of total jobs
	 * 
	 * @return number of jobs
	 */
	public int getJobsSize() {
		return totalJobs.size();
	}

	/**
	 * click on first job
	 */
	public void clickOnJob() {
		totalJobs.get(0).click();
	}

	/**
	 * select required customer
	 * 
	 * @param name
	 */
	public void clickOnCustomer(String name) {
		// driver.scrollTo(name);
		driver.findElement(
				By.xpath("//android.widget.TextView[@text='" + name + "']"))
				.click();
	}

	/**
	 * get the title of page
	 * 
	 * @return title text
	 */
	public String getTitle() {
		return pendingJobsHeading.getText();
	}

	/**
	 * get the error msg when no jobs are assigned
	 * 
	 * @return error message
	 */
	public String getErrorMsg() {
		return errorMsg.getText();
	}

	/**
	 * navigate back
	 */
	public void navigateBack() {
		navigateBack.click();
	}

}
