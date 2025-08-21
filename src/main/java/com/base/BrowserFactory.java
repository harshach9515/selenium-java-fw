package com.base;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	protected static WebDriver createLocalDriver(BrowserType browser) {
		try {
			switch (browser) {
			case CHROME:
				 //WebDriverManager.chromedriver().setup();
				return new ChromeDriver(BrowserOptions.getChromeOptions());
			case FIREFOX:
				return new FirefoxDriver(BrowserOptions.getFirefoxOptions());
			case IE:
				return new InternetExplorerDriver(BrowserOptions.getIEOptions());
			case SAFARI:
				return new SafariDriver();
			default:
				throw new RuntimeException("Browser not supported: " + browser);
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to create local WebDriver for browser: " + browser, e);
		}
	}

	protected static WebDriver createRemoteDriver(BrowserType browser) {
		try {
			String platform = ConfigReader.get("platform");
			String version = ConfigReader.get("version");
			String username = ConfigReader.get("sauceUsername");
			String accessKey = ConfigReader.get("sauceAccessKey");
			String build = ConfigReader.get("build");
			String name = ConfigReader.get("name");

			HashMap<String, Object> sauceOptions = new HashMap<>();
			sauceOptions.put("build", build);
			sauceOptions.put("name", name);

			URL sauceUrl = new URL(
					String.format("https://%s:%s@ondemand.us-west-1.saucelabs.com:443/wd/hub", username, accessKey));

			// Get browser options object
			MutableCapabilities options;
			switch (browser) {
			case CHROME:
				options = BrowserOptions.getChromeOptions();
				break;
			case FIREFOX:
				options = BrowserOptions.getFirefoxOptions();
				break;
			case IE:
				options = BrowserOptions.getIEOptions();
				break;
			case SAFARI:
				options = BrowserOptions.getSafariOptions();
				break;
			default:
				throw new RuntimeException("Unsupported browser: " + browser);
			}

			// Set common remote capabilities
			options.setCapability("platformName", platform);
			options.setCapability("browserVersion", version);
			options.setCapability("sauce:options", sauceOptions);

			return new RemoteWebDriver(sauceUrl, options);

		} catch (Exception e) {
			throw new RuntimeException("Failed to create remote WebDriver for browser: " + browser, e);
		}
	}
}
