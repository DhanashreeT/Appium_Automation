package com.plumber.pages;

import io.appium.java_client.android.AndroidDriver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.plumber.tests.BaseClass;

/**
 * The Class WorkHistory.
 * 
 * @author Dhanashree
 */
public class WorkHistory extends BaseClass {
	AndroidDriver driver = null;

	@FindBy(xpath = "//android.widget.TextView[@text='Past Jobs']")
	WebElement titleText;

	@FindBy(xpath = "//android.widget.ImageButton[@index='0']")
	WebElement navigateBack;

	@FindBy(id = "com.peninsula.plumber:id/customer_name")
	public WebElement customerName;

	@FindAll({ @FindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.peninsula.plumber:id/job_recycle_view']/android.widget.LinearLayout") })
	public List<WebElement> totalCustomers;

	/**
	 * Instantiates a new WorkHistory.
	 * 
	 * @param driver
	 *            the driver
	 */
	public WorkHistory(AndroidDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Get customer name
	 * 
	 * @return customer name
	 */
	public String getCustomerName() {
		return customerName.getText();
	}

	/**
	 * select required customer
	 * 
	 * @param name
	 * @throws InterruptedException
	 */
	public void clickOnCustomer(String name) throws InterruptedException {
		driver.scrollTo(name).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.ImageButton[@index='0']")));
		// driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+name+"\").instance(0))").click();
		// driver.scrollTo(name);
		// driver.findElement(By.xpath("//android.widget.TextView[@text='"+name+"']")).click();
	}

}
