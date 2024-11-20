package com.juaracoding.pages.Admin;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class IzinPulangCepatPage {
    private WebDriver driver;
    private By startDateErrorMessage;

    public IzinPulangCepatPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1kei35f']")
    private WebElement txtIzinCepat;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement txtSearch;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSearch;

    @FindBy(xpath = "//button[@type='button' and contains(text(), 'Reset')]")
    private WebElement btnReset;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-7 MuiGrid-grid-lg-8 css-kw2xn2']//div[1]//div[1]//div[1]//button[1]//*[name()='svg']")
    private WebElement btnCalender;

    @FindBy(xpath = "//input[@placeholder='Early']")
    private WebElement setStartDate;

    @FindBy(xpath = "// input[@placeholder='Continuous']")
    private WebElement setEndDate;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div/button[1]")
    private WebElement btnFilter;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'MuiButton-containedPrimary') and contains(text(), 'Terapkan')]")
    private WebElement btnFilterTerapkan;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    private WebElement txtNameFilter;

    @FindBy(xpath = "//button[contains(@class, 'MuiButton-textSecondary')]")
    private WebElement btnBatal;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium MuiAutocomplete-clearIndicator css-1gjoajr' and @type='button']")
    private WebElement btnX;

    @FindBy(xpath = "//input[@placeholder='Cari Departemen']")
    private WebElement txtCariDepartement;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> tableRows;

    // Assertion
    public void setNama(String Nama) {
        txtSearch.sendKeys(Nama);
    }

    public void setTanggal(String startDate, String endDate) {
        btnCalender.click();
        setStartDate.click();
        setStartDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        setStartDate.sendKeys(Keys.DELETE);
        setStartDate.sendKeys(startDate);
        setEndDate.click();
        setEndDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        setEndDate.sendKeys(Keys.DELETE);
        setEndDate.sendKeys(endDate);
    }

    public String getIzinCepatText() {
        return txtIzinCepat.getText();
    }

    public void setBtnFilter() {
        btnFilter.click();
    }

    public void setNameDepartement(String departement) {
        txtCariDepartement.click();
        txtCariDepartement.sendKeys(departement);
        Utils.delay(2);
        txtCariDepartement.sendKeys(Keys.ARROW_DOWN);
        Utils.delay(2);
        txtCariDepartement.sendKeys(Keys.ENTER);
        Utils.delay(2);
    }

    public void setNameTidakDepartement(String departement) {
        txtCariDepartement.click();
        txtCariDepartement.sendKeys(departement);
    }

    public void setBtnFilterTerapkan() {
        btnFilterTerapkan.click();
    }

    public void setBtnBatal() {
        btnBatal.click();
    }

    public String getTxtCariDepartemen() {
        return txtCariDepartement.getText();
    }

    public void clickSearch() {
        btnSearch.click();
    }

    public void setBtnX(){
        btnX.click();
    }

    public void setReset() {
        btnReset.click();
    }

    public String getTableRowText(int rowIndex) {
        try {
            if (tableRows.isEmpty()) {
                throw new NoSuchElementException("Table is empty, no rows available.");
            } // Mengembalikan null jika tabel kosong

            if (rowIndex >= 0 && rowIndex < tableRows.size()) { // Memeriksa apakah indeks baris valid
                return tableRows.get(rowIndex).getText(); // Mengembalikan teks dari baris yang valid
            } else {
                throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex); // Jika indeks baris tidak valid, lemparkan exception
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) { // Menangkap exception jika terjadi kesalahan dan menampilkan pesan error
            System.out.println("Error: " + e.getMessage());
            return null; // Mengembalikan null atau nilai lain sesuai kebutuhan
        }
    }

    public List<String> getAllTableRowsText() {
        List<String> rowTexts = new ArrayList<>();
        try {
            if (tableRows.isEmpty()) {
                throw new NoSuchElementException("Table is empty, no rows available.");
            }

            for (WebElement row : tableRows) {
                rowTexts.add(row.getText());
            }
            return rowTexts;

        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if the table is empty
        }
    }

    public String getAppliedDepartmentFilterText() {
        return txtCariDepartement.getAttribute("value");
    }

}

