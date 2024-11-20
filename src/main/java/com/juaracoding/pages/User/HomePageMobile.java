package com.juaracoding.pages.User;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageMobile {
    private WebDriver driver;

    public HomePageMobile() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1lkqrnd'][normalize-space()='Sakit']")
    private WebElement menuSakit;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div/div[1]/div[3]/div/div/div[5]/div/div/a/p")
    private WebElement menuCuti;

    @FindBy(xpath = "//p[contains(@class, 'MuiTypography-body1') and text()='3']")
    private WebElement jmlhSakit;

    @FindBy(xpath = "//p[contains(@class, 'MuiTypography-body1') and text()='0']")
    private WebElement jmlhCuti;

    public void setMenuSakit() {
        menuSakit.click();
    }
    public void setMenuCuti() {
        menuCuti.click();
    }

    public String getJmlhSakit() {
        return jmlhSakit.getText();
    }

    public String getJmlhCuti() {
        return jmlhCuti.getText();
    }
}
