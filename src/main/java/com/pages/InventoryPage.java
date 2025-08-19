package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BaseCase;

public class InventoryPage extends BaseCase {

    @FindBy(css = "div.header_label div.app_logo")
    private WebElement homePage;

    @FindBy(xpath = "//div[@data-test='inventory-item-name']")
    private List<WebElement> productList;

    @FindBy(css = "span[data-test='shopping-cart-badge']")
    private WebElement cartIcon;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void verifyHomePage() {
        if (!isPageLoaded(homePage)) {
            throw new RuntimeException("Inventory Page not loaded");
        }
        log.info("verifyHomePage() successful | Inventory Page is displayed");
    }

    public void clickOnProduct(String productName) {
        boolean productFound = false;
        for (WebElement product : productList) {
            if (product.getText().equalsIgnoreCase(productName)) {
                WebElement addToCart = product.findElement(
                        By.xpath("./ancestor::div[@class='inventory_item']//div[@class='pricebar']/button"));
                click(addToCart);
                log.info("clickOnProduct() successful | Clicked on '" + productName + "'");
                productFound = true;
                break;
            }
        }
        if (!productFound) {
            throw new RuntimeException("Product '" + productName + "' not found in product list");
        }
    }

    public void clickOnCart() {
        click(cartIcon); // BaseCase click() handles exceptions
        log.info("clickOnCart() successful | Cart icon clicked");
    }
}
