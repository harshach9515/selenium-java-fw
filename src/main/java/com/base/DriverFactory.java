package com.base;

import org.openqa.selenium.WebDriver;

import com.utils.ConfigReader;
import com.utils.LogManager;

public class DriverFactory {

    protected static org.apache.log4j.Logger log = LogManager.getLogger(DriverFactory.class);

    public static WebDriver createDriver(BrowserType browser) {
        try {
            TargetType target = TargetType.valueOf(ConfigReader.get("target").toUpperCase());
            log.info("Initializing " + target + " driver for browser: " + browser);

            switch (target) {
                case LOCAL:
                    return BrowserFactory.createLocalDriver(browser);
                case REMOTE:
                    return BrowserFactory.createRemoteDriver(browser);
                default:
                    throw new RuntimeException("Unsupported target type: " + target);
            }
        } catch (IllegalArgumentException e) {
            log.error("Invalid target type provided in config", e);
            throw new RuntimeException("Invalid target type in config: " + ConfigReader.get("target"), e);
        } catch (Exception e) {
            log.error("Failed to create WebDriver for browser: " + browser, e);
            throw new RuntimeException("Failed to create WebDriver for browser: " + browser, e);
        }
    }
}
