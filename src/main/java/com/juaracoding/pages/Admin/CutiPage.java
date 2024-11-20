package com.juaracoding.pages.Admin;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class CutiPage {
    private WebDriver driver;

    public CutiPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[contains(@class, 'MuiTypography-body1') and contains(@class, 'css-1kei35f') and text()='Cuti']\n")
    private WebElement txtCuti;

    @FindBy(xpath = "//input[@id='search']")
    private WebElement txtSearch;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSearch;

    @FindBy(xpath = "//button[@type='button' and contains(text(), 'Reset')]")
    private WebElement btnReset;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-7 MuiGrid-grid-lg-8 css-kw2xn2']//div[1]//div[1]//div[1]//button[1]//*[name()='svg']")
    private WebElement btnCalender;

    @FindBy(xpath = "//div[@class='rdrDateDisplay']//input[@placeholder='Early']\n")
    private WebElement setStartDate;

    @FindBy(xpath = "//div[@class='rdrDateDisplay']//input[@placeholder='Continuous']\n")
    private WebElement setEndDate;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/div/div[1]/div/div[2]/form/div/div[2]/div/button[1]")
    private WebElement btnFilter;

    @FindBy(xpath = "//form//div[contains(@class, 'MuiDialogTitle-root') and contains(text(), 'Filter By')]")
    private WebElement modalFilter;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    private WebElement txtNameFilter;

    @FindBy(xpath = "//button[@type='submit' and contains(@class, 'MuiButton-containedPrimary') and contains(text(), 'Terapkan')]\n")
    private WebElement btnFilterTerapkan;

    @FindBy(xpath = "//button[contains(@class, 'MuiButton-text') and contains(text(), 'Batal')]")
    private WebElement btnBatal;

    @FindBy(xpath = "//button[@title='Clear']")
    private WebElement btnX;

    @FindBy(xpath = "//input[@placeholder='Cari Departemen']")
    private WebElement txtCariDepartement;

    @FindBy(xpath = "//tbody/tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//li[button[contains(text(), 'Edit')]]")
    private WebElement tombolAksiEdit;

    @FindBy(xpath = "//label[contains(text(), 'Atasan 2')]")
    private WebElement txtAtasan2;

    @FindBy(xpath = "//div[contains(@class, 'MuiFormControl-root')]/label[contains(text(), 'Atasan 3')]/following-sibling::p")
    private WebElement txtAtasan3;

    @FindBy(xpath = "//button[contains(text(), 'Ubah Data')]")
    private WebElement btnUbahDataEdit;


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
        Utils.delay(2);
        setEndDate.click();
        setEndDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        setEndDate.sendKeys(Keys.DELETE);
        setEndDate.sendKeys(endDate);
        Utils.delay(2);
    }

    public String getCutiText() {
        return txtCuti.getText();
    }

    public void setBtnFilter() {
        btnFilter.click();
    }

    public void setNameDepartement(String departement) {
        txtCariDepartement.sendKeys(departement);
        Utils.delay(2);
        txtCariDepartement.sendKeys(Keys.ARROW_DOWN);
        Utils.delay(2);
        txtCariDepartement.sendKeys(Keys.ENTER);
    }

    public void setNameTidakDepartement(String departement) {
        txtCariDepartement.sendKeys(departement);
    }

    public void setBtnFilterTerapkan() {
        btnFilterTerapkan.click();
    }

    public void setBtnBatal() {
        btnBatal.click();
    }

    public String getTxtNameFilter() {
        return txtNameFilter.getText();
    }

    public void clickSearch() {
        btnSearch.click();
    }

    public void setBtnX() {
        btnX.click();
    }

    public void setReset() {
        btnReset.click();
    }

    public void setEdit() {
        tombolAksiEdit.click();
    }

    public void setTombolUbahData() {
        btnUbahDataEdit.click();
    }

    public String editUpliner(String v2, String v3) {
        try {
            // Validasi visibilitas elemen Atasan 2
            if (txtAtasan2.isDisplayed() && txtAtasan2.isEnabled()) {
                txtAtasan2.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                txtAtasan2.sendKeys(Keys.DELETE);
                txtAtasan2.sendKeys(v2);
            } else {
                throw new ElementNotInteractableException("Atasan 2 field is not interactable.");
            }

            // Validasi visibilitas elemen Atasan 3
            if (txtAtasan3.isDisplayed() && txtAtasan3.isEnabled()) {
                txtAtasan3.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                txtAtasan3.sendKeys(Keys.DELETE);
                txtAtasan3.sendKeys(v3);
            } else {
                throw new ElementNotInteractableException("Atasan 3 field is not interactable.");
            }

            return "Atasan 2 dan Atasan 3 berhasil diperbarui";
        } catch (ElementNotInteractableException e) {
            System.out.println("Error: " + e.getMessage());
            return "Gagal memperbarui upliner: " + e.getMessage();
        }
    }

    public String getTableRowText(int rowIndex) {
        try {
            if (tableRows.isEmpty()) {
                System.out.println("Table is empty. Ensure data is available for the given date range.");
                return null; // Mengembalikan null jika tabel kosong
            }

            if (rowIndex >= 0 && rowIndex < tableRows.size()) { // Memeriksa apakah indeks baris valid
                return tableRows.get(rowIndex).getText(); // Mengembalikan teks dari baris yang valid
            } else { //
                throw new IndexOutOfBoundsException("Row index out of bounds: " + rowIndex);  // Jika indeks baris tidak valid, lemparkan exception
            }
        } catch (NoSuchElementException | IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage()); // Menangkap exception jika terjadi kesalahan dan menampilkan pesan error
            return null; // Mengembalikan null jika terjadi error
        }
    }

    public void findAndClickActionButton()  {
        boolean foundActionButton = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollLeft += 1000;", tableRows);

        Utils.delay(5);

        for (WebElement row : tableRows) {
            // Mencari tombol aksi pada setiap baris
            List<WebElement> actionButtons = row.findElements(By.xpath("//button[@aria-label='action']"));

            // Jika tombol aksi ditemukan, klik tombol tersebut
            if (actionButtons.size() > 0) {
                actionButtons.get(0).click();
                foundActionButton = true;
                break; // Keluar dari loop jika tombol ditemukan
            }
        }

        if (!foundActionButton) {
            System.out.println("Tombol aksi tidak ditemukan pada baris pertama, memeriksa baris berikutnya...");
        }
    }




    public String getAppliedDepartmentFilterText() {
        return txtCariDepartement.getAttribute("value");
    }
}



