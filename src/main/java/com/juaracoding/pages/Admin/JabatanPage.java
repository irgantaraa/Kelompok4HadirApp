package com.juaracoding.pages.Admin;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class JabatanPage {
    private WebDriver driver;
    private By startDateErrorMessage;

    public JabatanPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1kei35f']")
    private WebElement txtJabatan;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement txtFieldSearch;

    @FindBy(xpath = "//button[normalize-space()='Reset']")
    private WebElement btnReset;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSearch;

    // modal tambahkan
    @FindBy(xpath = "//button[normalize-space()='Tambahkan']")
    private WebElement btnTambahkan;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement txtTambahkanName;

    @FindBy(xpath = "//input[@id='level']")
    private WebElement txtTambahkanLevel;

    @FindBy(xpath = "//button[text()='Tambah']")
    private WebElement btnTambah;

    @FindBy(xpath = "//button[text()='Batal']\n")
    private WebElement btnBatal;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> tableRows;

    // action button tabel
    @FindBy(xpath = "(//button[@aria-label='action'])[1]")
    private WebElement btnActionTambahkan; //baris 2

    @FindBy(xpath = "//li[text()='Edit']")
    private WebElement btnEditTabel;

    @FindBy(xpath = "//li[text()='Delete']")
    private WebElement btnDeleteTabel;

    @FindBy(xpath = "//div[@class='MuiDialogActions-root MuiDialogActions-spacing css-1vskg8q']//button[text()='Ya']\n")
    private WebElement btnYaTabel;

    @FindBy(xpath = "//div[@class='MuiDialogActions-root MuiDialogActions-spacing css-1vskg8q']//button[text()='Tidak']\n" )
    private WebElement btnTidakTabel;

    @FindBy(xpath = "//button[text()='Simpan']")
    private WebElement btnSimpanEditModal;

    @FindBy(xpath = "//input[contains(@class, 'MuiInputBase-input') and @placeholder='PilihUnit']")
    private WebElement inputunit;

    //Assertion


    public String gettxtJabatanLogo() {
        return txtJabatan.getText();
    }

    public void searchJabatan(String level) {
        txtFieldSearch.sendKeys(level);
    }

    public String getSearchField() {
        return txtFieldSearch.getText();
    }

    public void setBtnSearch() {
        btnSearch.click();
    }

    public void setBtnReset() {
        btnReset.click();
    }

    public void setBtnTambahkanNama(String Name, String Level) {
        Utils.delay(3);
        btnTambahkan.click();
        txtTambahkanName.sendKeys(Name);
        txtTambahkanLevel.sendKeys(Level);
        Utils.delay(2);
        btnTambah.click();
    }

    public void setBtnTambaModal(){ //button tambah modal tambah
        btnTambah.click();
    }

    public void setBtnAction() { // button action atau 3 titik
        btnActionTambahkan.click();
    }

    public void setBtnSimpanEditModal() {
        btnSimpanEditModal.click();
    }
    public void setBtnBatal() {
        btnBatal.click();
    }

    public void deleteTabel() {
        btnDeleteTabel.click();
        Utils.delay(2);
        btnYaTabel.click();

    }


    public void editData(String Name, String Level) {
        Utils.delay(2);
        btnEditTabel.click();
        txtTambahkanName.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtTambahkanName.sendKeys(Keys.DELETE);
        txtTambahkanName.sendKeys(Name);
        txtTambahkanLevel.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        txtTambahkanLevel.sendKeys(Keys.DELETE);
        txtTambahkanLevel.sendKeys(Level);
    }

    public String getTableRowText(int rowIndex) {
        try {
            if (tableRows.isEmpty()) {
                throw new NoSuchElementException("Table is empty, no rows available.");
            }

            if (rowIndex >= 0 && rowIndex < tableRows.size()) {
                return tableRows.get(rowIndex).getText();
            } else {
                throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex);
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // Mengembalikan null atau nilai lain sesuai kebutuhan
        }
    }

    public String getAllTableRowTexts() {
        try {
            if (tableRows.isEmpty()) {
                throw new NoSuchElementException("Table is empty, no rows available.");
            }

            StringBuilder allRowText = new StringBuilder();
            for (WebElement row : tableRows) {
                allRowText.append(row.getText()).append("\n");  // Adding new line for each row's text
            }

            return allRowText.toString().trim();  // Trim to remove trailing newline
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
            return null; // Return null or any other value as needed
        }
    }

    public String vallNullName(){
        return txtTambahkanName.getAttribute("validationMessage");
    }
    public String vallNullLevel(){
        return txtTambahkanLevel.getAttribute("validationMessage");
    }
}
