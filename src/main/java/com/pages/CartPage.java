package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import com.base.BaseCase;

public class CartPage extends BaseCase{
	
	@FindBy(css = "span[data-test='title']")
	private WebElement cartPage;
	
	@FindBy(css = "div[data-test='inventory-item-name']")
	private WebElement productName;
	
	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	
	
	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	public void verifyCartPage() {
		try {
			wait.until(ExpectedConditions.visibilityOf(cartPage));
			Assert.assertTrue(isPageLoaded(cartPage));
			Reporter.log("Cart Page is displayed");
		} catch (Throwable e) {
			Reporter.log("CartPage failed to load : " + e.getMessage());
			throw e;
		}
	}
	
	public void verifyProduct(String productname) {
		try {
			Assert.assertEquals(productName.getText().trim(), productname);
			Reporter.log("Product '"+productname+"' added to cart");
			Reporter.log("'"+productname+"' is added to cart");
		}catch (Exception e) {
			Reporter.log("Product '"+productname+"' Not Added to Cart: " + e.getMessage());
			throw e;
		}
	}
	
	public void clickCheckoutButton() {
		try {
			click(checkoutButton);
			Reporter.log("Clicked on checkout button");
		}catch (Exception e) {
			Reporter.log("Clicking On CheckOut Button Failed :"+ e.getMessage());
		}
	}

}
