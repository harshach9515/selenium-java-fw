package com.base;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public static void initDriver(String browser) {
    	BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        WebDriver driver = DriverFactory.createDriver(browserType);
        driver.manage().window().maximize();
        tdriver.set(driver);
    }

    public static WebDriver getDriver() {
        return tdriver.get();
    }

    public static void quitDriver() {
        if (tdriver.get() != null) {
            tdriver.get().quit();
            tdriver.remove();
        }
    }
}
