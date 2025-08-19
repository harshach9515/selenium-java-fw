package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BaseCase;

public class LoginPage extends BaseCase {

    @FindBy(id = "user-name")
    private WebElement inputUsername;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login-button")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate(String URL) {
        goTo(URL); // BaseCase goTo() handles exceptions
        log.info("navigate() successful | URL: " + URL);
    }

    public void logIntoApp(String username, String password) {
        sendText(inputUsername, username); // BaseCase handles exceptions
        sendText(inputPassword, password);
        click(signInButton);
        log.info("logIntoApp() successful | User: " + username);
    }
}
