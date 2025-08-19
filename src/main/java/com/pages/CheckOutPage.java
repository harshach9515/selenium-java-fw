package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import com.base.BaseCase;

public class CheckOutPage extends BaseCase {

	@FindBy(css = "span[data-test='title']")
	private WebElement checkoutPage;

	@FindBy(id = "first-name")
	private WebElement inputFirstname;

	@FindBy(id = "last-name")
	private WebElement inputLastname;

	@FindBy(id = "postal-code")
	private WebElement inputZipcode;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "finish")
	private WebElement finishButton;

	@FindBy(css = "h2[class='complete-header']")
	private WebElement successMessage;

	public CheckOutPage(WebDriver driver) {
		super(driver);
	}

	public void verifyCheckoutPage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(checkoutPage));
			Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), null);
			Assert.assertTrue(isPageLoaded(checkoutPage));
		} catch (Throwable e) {
			Reporter.log("Checkout page failed to load : " + e.getMessage());
			throw e;
		}
	}

	public void fillCustomerDetails(String firstName, String lastName, String zipcode) {
		try {
			sendText(inputFirstname, firstName);
			sendText(inputLastname, lastName);
			sendText(inputZipcode, zipcode);
		} catch (Exception e) {
			Reporter.log("Failed to fill customer details: " + e.getMessage());
			throw e;
		}
	}

	public void clickContinueButton() {
		try {
			click(continueButton);
			Reporter.log("Clicked on continue button");
		} catch (Exception e) {

		}
	}

	public void clickFinishButton() {
		try {
			wait.until(ExpectedConditions.visibilityOf(finishButton));
			click(finishButton);
			Reporter.log("Clicked on finish button");
		} catch (Exception e) {

		}
	}

	public void verifySuccessMessage(String message) {
		try {
			wait.until(ExpectedConditions.visibilityOf(successMessage));
			Assert.assertTrue(successMessage.getText().equalsIgnoreCase(message));
			Reporter.log("Order placed Succesfully : '" + message + "'");
		} catch (Throwable e) {
			Reporter.log("Checkout page failed to load : " + e.getMessage());
			throw e;
		}
	}

}
