package com.base;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.reports.Report;

public class BaseCase {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Logger log = Logger.getLogger(this.getClass());

	public BaseCase(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		PageFactory.initElements(driver, this);
	}

	public void goTo(String url) {
		try {
			driver.get(url);
			log.info("Navigated to URL: " + url);
			Report.info("Navigated to URL: " + url);
		} catch (Exception e) {
			log.error("goTo() failed | URL: " + url + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("goTo() failed: " + e.getMessage(), e);
		}
	}

	public void sendText(WebElement element, String text) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			element.clear();
			element.sendKeys(text);
			log.info("sendText() successful | Text: '" + text + "' | Element: " + element);
			Report.info("Enter text : " +text);
		} catch (Exception e) {
			log.error("sendText() failed | Text: '" + text + "' | Element: " + element + " | Error: " + e.getMessage(),
					e);
			throw new RuntimeException("sendText() failed: " + e.getMessage(), e);
		}
	}

	public void click(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			log.info("click() successful | Element: " + element);
			Report.info("Click on :"+ element);
		} catch (Exception e) {
			log.error("click() failed | Element: " + element + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("click() failed: " + e.getMessage(), e);
		}
	}

	public boolean isDisplayed(WebElement element) {
		try {
			boolean displayed = element.isDisplayed();
			log.info("isDisplayed() | Element: " + element + " | Status: " + displayed);
			return displayed;
		} catch (Exception e) {
			log.error("isDisplayed() failed | Element: " + element + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("isDisplayed() failed: " + e.getMessage(), e);
		}
	}

	public void scrollIntoView(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			log.info("scrollIntoView() successful | Element: " + element);
		} catch (Exception e) {
			log.error("scrollIntoView() failed | Element: " + element + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("scrollIntoView() failed: " + e.getMessage(), e);
		}
	}

	public void hoverOverElement(WebElement element) {
		try {
			new Actions(driver).moveToElement(element).perform();
			log.info("hoverOverElement() successful | Element: " + element);
		} catch (Exception e) {
			log.error("hoverOverElement() failed | Element: " + element + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("hoverOverElement() failed: " + e.getMessage(), e);
		}
	}

	public void selectFromDropdown(WebElement element, String selection, String value) {
		try {
			Select select = new Select(element);
			switch (selection.toLowerCase()) {
			case "text":
				select.selectByVisibleText(value);
				break;
			case "index":
				select.selectByIndex(Integer.parseInt(value));
				break;
			case "value":
				select.selectByValue(value);
				break;
			default:
				throw new IllegalArgumentException("Invalid selection type");
			}
			log.info("selectFromDropdown() successful | Selection: " + selection + " | Value: " + value);
		} catch (Exception e) {
			log.error("selectFromDropdown() failed | Selection: " + selection + " | Value: " + value + " | Error: "
					+ e.getMessage(), e);
			throw new RuntimeException("selectFromDropdown() failed: " + e.getMessage(), e);
		}
	}

	public List<WebElement> getElements(By locator) {
		try {
			List<WebElement> elements = driver.findElements(locator);
			log.info("getElements() successful | Locator: " + locator + " | Count: " + elements.size());
			return elements;
		} catch (Exception e) {
			log.error("getElements() failed | Locator: " + locator + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("getElements() failed: " + e.getMessage(), e);
		}
	}

	public boolean verifyPageTitle(String expectedTitle) {
		try {
			boolean match = driver.getTitle().equals(expectedTitle);
			log.info("verifyPageTitle() | Expected: " + expectedTitle + " | Match: " + match);
			return match;
		} catch (Exception e) {
			log.error("verifyPageTitle() failed | Expected: " + expectedTitle + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("verifyPageTitle() failed: " + e.getMessage(), e);
		}
	}

	public boolean isPageLoaded(WebElement element) {
		try {
			boolean displayed = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
			log.info("isPageLoaded() | Element: " + element + " | Status: " + displayed);
			return displayed;
		} catch (Exception e) {
			log.error("isPageLoaded() failed | Element: " + element + " | Error: " + e.getMessage(), e);
			throw new RuntimeException("isPageLoaded() failed: " + e.getMessage(), e);
		}
	}
}
