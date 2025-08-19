package com.base;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserOptions {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*",
                             "--disable-blink-features=AutomationControlled",
                             "--disable-extensions",
                             "--no-sandbox",
                             "--disable-dev-shm-usage",
                             "--incognito");
        // options.addArguments("--headless"); // optional
        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        // options.addArguments("-headless"); // optional
        return options;
    }

    public static InternetExplorerOptions getIEOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.ignoreZoomSettings();
        options.introduceFlakinessByIgnoringSecurityDomains();
        return options;
    }
    
    public static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        return options;
    }
}
