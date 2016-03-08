package com.plumber.tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

/**
     To set up all required capabilities
*/
public class BaseClass {
	@SuppressWarnings("rawtypes")
	AndroidDriver driver = null;

	@SuppressWarnings("rawtypes")
	public void initProcess() throws MalformedURLException {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "/fileApk");
		File app = new File(appDir, "PunePlumbers.apk");

		// Set up desired capabilities and pass the Android app-activity and
		// app-package to Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("BROWSER_NAME", "Android");
		capabilities.setCapability("VERSION", "5.0.1");
		// capabilities.setCapability("deviceName", "Emulator");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "ZX1D629BD6");
		// capabilities.setCapability("deviceName", "192.168.56.103:5555");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.peninsula.plumber");
		capabilities.setCapability("appActivity", "com.peninsula.plumber.activity.SplashScreenActivity");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4726/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
}
