package com.juaracoding.pages.User;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MobileLoginPage {
    private WebDriver driver;

    public MobileLoginPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='email']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnLogin;


    public void loginUser(String username, String password) {
        this.username.sendKeys(username);
        Utils.delay(2);
        this.password.sendKeys(password);
    }

    public void setBtnLogin() {
        btnLogin.click();
    }
}
