package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The Class CompletedJobPage.
 * 
 * @author Dhanashree
 */
public class CompletedJobPage {
	AndroidDriver driver = null;

	@FindBy(xpath = "//android.widget.TextView[@text='Completed Job']")
	WebElement titleText;

	@FindBy(id = "com.peninsula.plumber:id/customer_name")
	WebElement customerName;

	@FindBy(xpath = "//android.widget.ImageButton[@index='0']")
	WebElement navigateBack;

	/**
	 * Instantiates a new Completed job page.
	 * 
	 * @param driver
	 *            the driver
	 */
	public CompletedJobPage(AndroidDriver driver) {

		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Get the Customer name
	 * 
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName.getText();
	}

	/**
	 * Get the page title
	 * 
	 * @return title text
	 */
	public String getTitle() {
		return titleText.getText();
	}

	/**
	 * navigate back
	 */
	public void navigateBack() {
		navigateBack.click();
	}
}
