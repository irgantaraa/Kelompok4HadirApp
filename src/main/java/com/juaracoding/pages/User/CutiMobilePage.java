package com.juaracoding.pages.User;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CutiMobilePage {
    private WebDriver driver;

    public CutiMobilePage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    // Locators for elements in the "List Request Cuti" section
    @FindBy(xpath = "//p[contains(text(), 'List Request Cuti')]")
    private WebElement txtListRequestCuti;

    @FindBy(xpath = "//p[contains(text(), 'Total :')]")
    private WebElement txtTotalCuti;

    @FindBy(xpath = "//div[contains(@class, 'MuiSnackbarContent-message')]")
    private WebElement txtAlert;

    @FindBy(xpath = "//button[contains(text(), 'Ajukan Cuti')]")
    private WebElement btnAjukanCuti;


    // Locators for elements in the "Ajukan Cuti" form
    @FindBy(xpath = "//p[contains(text(), 'Ajukan Cuti')]")
    private WebElement txtAjukanCuti;

    @FindBy(xpath = "//button[contains(text(), 'Form Cuti')]")
    private WebElement btnFormCuti;

    @FindBy(xpath = "//button[contains(text(), 'Info Cuti')]")
    private WebElement btnInfoCuti;

    @FindBy(xpath = "//label[contains(text(), 'Pilih Tipe Cuti')]")
    private WebElement lblPilihTipeCuti;

    @FindBy(xpath = "//ul[@role='listbox']")
    private WebElement PilihTipeDropdown;

    @FindBy(xpath = "//*[@id=\"leave_type_id\"]")
    private WebElement dropdownTipeCuti;

    @FindBy(css = "svg.MuiSvgIcon-root.MuiSvgIcon-colorPrimary")
    private WebElement calenderIcon;

    @FindBy(xpath = "//div[@class='rdrDateDisplay']//input[@placeholder='Early']\n")
    private WebElement setStartDate;

    @FindBy(xpath = "//div[@class='rdrDateDisplay']//input[@placeholder='Continuous']\n")
    private WebElement setEndDate;

    @FindBy(xpath = "//button[text()='Simpan']")
    private WebElement btnSimpan;

    @FindBy(xpath = "//p[contains(@class, 'MuiTypography-root') and contains(@class, 'css-1ub5lza')]")
    private WebElement txtTanggalRangel;

    @FindBy(xpath = "//textarea[@id='notes']")
    private WebElement txtCatatan;

    @FindBy(xpath = "//button[contains(text(), 'Reset')]")
    private WebElement btnReset;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnAjukan;

    // Web elements for each option
    @FindBy(css = "#mui-10 > li.MuiButtonBase-root.MuiMenuItem-root.Mui-disabled.MuiMenuItem-gutters.Mui-disabled.MuiMenuItem-root.Mui-disabled.MuiMenuItem-gutters.css-1fh5cnl")
    private WebElement pilihTipeAbsen;

    @FindBy(xpath = "//li[@data-value='dd733981-32dd-47bf-96a5-428e3983df64']")
    private WebElement khitananPembabtisan;

    @FindBy(xpath = "//li[@data-value='7d28f8a8-4d62-44cf-aed9-5e175da0abbc']")
    private WebElement pernikahanAnak2Hari;

    @FindBy(xpath = "//li[@data-value='d69b6b99-047e-4d9d-80e2-20c3967c01b3']")
    private WebElement cutiPersalinan;

    @FindBy(xpath = "//li[@data-value='993e0012-3979-4039-a237-8048fce8e6d6']")
    private WebElement cutiBerdukaSaudaraKandung;

    @FindBy(xpath = "//li[@data-value='993e0012-3979-4039-a237-8048fce8e6d2']")
    private WebElement cutiBerdukaOrangTua;

    @FindBy(xpath = "//li[@data-value='9d7ad169-78ca-4bdf-a391-58b95178d041']")
    private WebElement pernikahanAnak1Hari;

    @FindBy(xpath = "//li[@data-value='ff876ff1-0011-4edb-a482-9642b0acb315']")
    private WebElement pernikahanDiriSendiri;

    @FindBy(xpath = "//li[@data-value='4e64bc87-cdac-40a7-a9fe-7fba21f8b1e7']")
    private WebElement cutiTahunan;


    // Method to click each option
    public void clickPilihTipeAbsen() {
        dropdownTipeCuti.click();
    }

    public void clickKhitananPembabtisan() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        khitananPembabtisan.click();
    }

    public void clickPernikahanAnak2Hari() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        pernikahanAnak2Hari.click();
    }

    public void clickCutiPersalinan() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        cutiPersalinan.click();
    }

    public void clickCutiBerdukaSaudaraKandung() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        cutiBerdukaSaudaraKandung.click();
    }

    public void clickCutiBerdukaOrangTua() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        cutiBerdukaOrangTua.click();
    }

    public void clickPernikahanAnak1Hari() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        pernikahanAnak1Hari.click();
    }

    public void clickPernikahanDiriSendiri() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        pernikahanDiriSendiri.click();
    }

    public void clickCutiTahunan() {
        dropdownTipeCuti.click();
        Utils.delay(3);
        cutiTahunan.click();
    }

    //Assertion
    public String getListRequestCutiText() {
        return txtListRequestCuti.getText();
    }

    public String getTotalCutiText() {
        return txtTotalCuti.getText();
    }
    public String getTxtAlert() {
        return txtAlert.getText();
    }

    public boolean isAlertDisplayed() {
        try {
            return txtAlert.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickAjukanCuti() {
        btnAjukanCuti.click();
    }

    // Methods to interact with the elements
    public void clickFormCuti() {
        btnFormCuti.click();
    }

    public void clickInfoCuti() {
        btnInfoCuti.click();
    }

    public void setTanggal(String startDate, String endDate) {
        calenderIcon.click();
        Utils.delay(5);
        setStartDate.click();
        setStartDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        setStartDate.sendKeys(Keys.DELETE);
        setStartDate.sendKeys(startDate);
        setEndDate.click();
        setEndDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        setEndDate.sendKeys(Keys.DELETE);
        setEndDate.sendKeys(endDate);
        btnSimpan.click();
    }
        public void setCatatan (String catatan){
            txtCatatan.clear();
            txtCatatan.sendKeys(catatan);
        }

        public void clickReset () {
            btnReset.click();
        }

        public void clickAjukan () {
            btnAjukan.click();
        }

    }




//Incorrect datetime value: 'Invalid date' for column 'leave_date_from' at row 1
//Kontrak anda sudah habis ditanggal tersebut!