package com.juaracoding.pages.Admin;

import com.juaracoding.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='app_logo']")
    private WebElement txtAppLogo;

    @FindBy(xpath = "//p[normalize-space()='Laporan']")
    private WebElement sideBarLaporan;

    @FindBy(xpath = "//p[normalize-space()='Izin Pulang Cepat']")
    private WebElement sideBarIzinPulangCepat;

    @FindBy(xpath = "//p[contains(@class, 'MuiTypography-body1') and contains(@class, 'css-aqx7sf') and text()='Cuti']\n")
    private WebElement sideBarCuti;

    @FindBy(xpath = "//div[contains(@class, 'sidebar__item')]//p[text()='Management']")
    private WebElement sideBarManagement;

    @FindBy(xpath = "//p[text()='Jabatan']")
    private WebElement sideBarJabatan;

    @FindBy(xpath = "//p[text()='Shifting']")
    private WebElement sideBarShifting;

    @FindBy(xpath ="//button[@aria-label='menu']")
    private WebElement btnMenuAdmin;

    @FindBy(xpath = "//button[text()='Logout']")
    private WebElement btnLogout;

    // logout
    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement menuBtn;

    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private WebElement logoutBtn;

    // Assertion
    public String getTxtAppLogo() {
        return txtAppLogo.getText();
    }

    public void setIzinPulangCepat() {
        sideBarLaporan.click();
        sideBarIzinPulangCepat.click();
    }

    public void setCuti() {
        sideBarLaporan.click();
        sideBarCuti.click();
    }

    public void setJabatan() {
        sideBarManagement.click();
        sideBarJabatan.click();
    }
    public void setShifting() {
        sideBarManagement.click();
        sideBarShifting.click();
    }
}

