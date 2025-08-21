package com.base;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.data.TestData;
import com.data.TestDataManager;
import com.utils.LogManager;

public class BaseTest {
	protected org.apache.log4j.Logger log = LogManager.getLogger(BaseTest.class);
	protected TestData testData;

	@BeforeMethod
	@Parameters("browser")
	public void setUp(Method method, String browser) {
		log.info("=== Starting '" + method.getName() + "' ===");
		try {
			DriverManager.initDriver(browser);
			log.info("Driver created successfully for test: " + method.getName());
			
			testData = TestDataManager.getDataForTest(method.getName());
		} catch (Exception e) {
			log.error("Driver creation failed: " + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public org.openqa.selenium.WebDriver getDriver() {
		return DriverManager.getDriver();
	}


	@AfterMethod
	public void tearDown(Method method) {
		try {
			DriverManager.quitDriver();
			log.info("Driver closed successfully");
			log.info("=== Completed '" + method.getName() + "' ===");
		} catch (Exception e) {
			log.error("Failed to close the driver: " + e.getMessage(), e);
		}
	}
}