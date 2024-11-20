package com.juaracoding.pages.User;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SakitMobilePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SakitMobilePage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "\"//p[contains(@class, 'MuiTypography-body1') and text()='Halaman Sakit']\"")
    private WebElement txtHalamanSakit;

    @FindBy(xpath = "//p[contains(@class, 'MuiTypography-body1') and contains(text(), 'Total :')]")
    private WebElement txtTotalSakit;

    @FindBy(xpath = "//p[text()='List Request Sakit']")
    private WebElement txtListSakit;


    @FindBy(xpath = "//div[@class='MuiPaper-root MuiCard-root']")
    private List<WebElement> tabelListSakit;

    @FindBy(xpath = "//button[contains(@class, 'MuiButton-containedPrimary') and contains(text(), 'Ajukan Sakit')]")
    private WebElement btnAjukanSakit;

    @FindBy(css = "svg.MuiSvgIcon-root.MuiSvgIcon-colorPrimary")
    private WebElement calenderIcon;

    @FindBy(xpath = "//input[@placeholder='Early']")
    private WebElement setStartDate;

    @FindBy(xpath = "//input[@placeholder='Continuous']")
    private WebElement setEndDate;

    @FindBy(css = "button.MuiButton-outlinedPrimary.css-1m6c5ra\n")
    private WebElement btnBatalCalender;

    @FindBy(xpath = "//button[contains(text(),'Simpan')]")
    private WebElement btnSimpanCalender;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement btnUpload;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    private WebElement btnReset;

    @FindBy(css = "button.MuiButton-containedPrimary.css-arawcv")
    private WebElement btnAjukanRequestSakit;

    @FindBy(xpath = "//div[@role='presentation']//div[contains(@class, 'MuiSnackbarContent-message') and text()='Anda sudah melakukan izin sakit di tanggal tersebut']")
    private WebElement txtAlertTanggalduplikat;

    @FindBy(xpath = "//div[contains(@class, 'MuiSnackbarContent-message')]")
    private WebElement txtAlert;


    public String getTxtAlert() {
        return txtAlert.getText();
    }

    public String getTxtTotal() {
        return txtTotalSakit.getText();
    }

    public String getTxtListSakit() {
        return txtListSakit.getText();
    }

    public void setAjukanSakit() {
        btnAjukanSakit.click();
    }

    public void setBtnAjukanRequestSakit() {
        btnAjukanRequestSakit.click();
    }

    public void setTxtAlertTanggalduplikat() {
        txtAlertTanggalduplikat.click();
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
        btnSimpanCalender.click();
    }

    public void setBtnBatalCalender(String startDate, String endDate) {
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
        btnBatalCalender.click();
    }

    public void setBtnUpload(String filePath) {
        // Send the file path to the hidden file input
        btnUpload.sendKeys(filePath);
    }

    // Method to upload a specific file (e.g., surat_sakit.jpg)
    public void setBtnUploadImage() {
        String filePath = "C:\\Users\\User\\Desktop\\Sakit\\surat_sakit.jpg";
        setBtnUpload(filePath);
        Utils.delay(10);
    }

    // Method to upload a .docx file
    public void setBtnUploadDocx() {
        String filePath = "C:\\Users\\User\\Desktop\\Sakit\\surat_sakit.docx";
        setBtnUpload(filePath);
        Utils.delay(10);
    }


    public void setBtnUploadAlone() {
        btnUpload.click();
    }

    public void setBtnReset() {
        btnReset.click();
    }

    public boolean isAlertDisplayed() {
        try {
            return txtAlert.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}


