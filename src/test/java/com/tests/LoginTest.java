package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.pages.InventoryPage;
import com.pages.LoginPage;
import com.utils.ConfigReader;

/**
 * 
 * @author hchalla
 *
 */
public class LoginTest extends BaseTest {

	@Test
	public void test_userCanLoginToApp() throws InterruptedException {
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.navigate(ConfigReader.get("baseUrl"));

		loginPage.logIntoApp(testData.getUsername(), testData.getPassword());

		InventoryPage homePage = new InventoryPage(getDriver());
		homePage.verifyHomePage();

	}
}
