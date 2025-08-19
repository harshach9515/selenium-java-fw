/**
 * 
 */
package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.CartPage;
import com.pages.CheckOutPage;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import com.utils.ConfigReader;

/**
 * @author hchalla
 *
 */
public class EcommerceTest extends BaseTest{
	
		@Test
		public void test_userCanAddAProductToCartAndPlaceOrder() throws InterruptedException {
		
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.navigate(ConfigReader.get("baseUrl"));
	
		loginPage.logIntoApp(testData.getUsername(), testData.getPassword());
		
		InventoryPage inventoryPage = new InventoryPage(getDriver());
		inventoryPage.verifyHomePage();
		inventoryPage.clickOnProduct(testData.getProductname());
		inventoryPage.clickOnCart();
		
		CartPage cartPage = new CartPage(getDriver());
		cartPage.verifyCartPage();
		cartPage.verifyProduct(testData.getProductname());
		cartPage.clickCheckoutButton();
		
		CheckOutPage checkOutPage = new CheckOutPage(getDriver());
		checkOutPage.verifyCheckoutPage();
		checkOutPage.fillCustomerDetails(testData.getFirstname(), testData.getLastname(), testData.getZipcode());
		checkOutPage.clickContinueButton();
		checkOutPage.clickFinishButton();
		checkOutPage.verifySuccessMessage("Thank you for your order!");
	}

}
