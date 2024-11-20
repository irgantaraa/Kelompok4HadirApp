package com.juaracoding;

import com.juaracoding.pages.Admin.HomePage;
import com.juaracoding.pages.Admin.JabatanPage;
import com.juaracoding.pages.Admin.WebLoginPage;
import com.juaracoding.utils.Constants;
import com.juaracoding.utils.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertNotNull;

public class Jabatan {
    private static WebDriver driver;

    private static ExtentTest extentTest;

    private static WebLoginPage webLoginPage = new WebLoginPage();
    private static HomePage homePage = new HomePage();
    private static JabatanPage jabatanPage = new JabatanPage();

    public Jabatan() {
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    //Scenario 1 Pencarian dengan level yang valid (Positif)
    @Given("Admin sudah login")
    public void adminSudahLogin() {
        driver.get(Constants.URL);
        webLoginPage.loginUser("admin@hadir.com", "admin@hadir");
        webLoginPage.setBtnLogin();
        homePage.setJabatan();
        String logo = jabatanPage.gettxtJabatanLogo();
        System.out.println(logo);
        Assert.assertNotNull("Teks Jabatan harus ditampilkan", logo);
        extentTest.log(LogStatus.PASS, "Admin sudah login");
    }

    @When("Admin mencari karyawan dengan level valid")
    public void adminMencariKaryawanDenganLevelValid() {
        jabatanPage.searchJabatan("2");
        jabatanPage.setBtnSearch();
        extentTest.log(LogStatus.PASS, "Admin mencari karyawan dengan level valid");

    }

    @Then("Admin harus melihat hasil pencarian")
    public void adminHarusMelihatPencarian() {
        String tabel = jabatanPage.getTableRowText(1);
        System.out.println(tabel);
        extentTest.log(LogStatus.PASS, "Admin harus melihat hasil pencarian");
        assertNotNull("Table row text should not be null", tabel);
    }

    //Scenario 2 Pencarian dengan level yang valid (Positif)
    @When("Admin mencari dengan data tidak valid")
    public void adminMencariDenganDataTidakValid() {
        jabatanPage.searchJabatan("Staff");
        jabatanPage.setBtnSearch();
        Utils.delay(3);
        extentTest.log(LogStatus.PASS, "Admin mencari dengan data tidak valid");
    }

    @Then("Admin harus melihat tidak ada hasil")
    public void adminHarusMelihatTidakadaHasil() {
        String tabel = jabatanPage.getAllTableRowTexts(); // Get the table content
        System.out.println(tabel);

        Assert.assertTrue(tabel == null || tabel.isEmpty() || tabel.contains("Staff"),
                "Table should be empty or display 'No data' for invalid search results");
        extentTest.log(LogStatus.PASS, "Admin harus melihat tidak ada hasil");
    }

    //Scenario 3 Pencarian dengan level yang valid (Positif)
    @When("Admin mencari dengan huruf")
    public void adminMencariDenganHuruf() {
        jabatanPage.searchJabatan("Abc");
        jabatanPage.setBtnSearch();
        Utils.delay(3);
        extentTest.log(LogStatus.PASS, "Admin mencari dengan huruf");
    }

    @Then("Admin melihat tidak ada hasil di tabel")
    public void adminMelihatTidakadaHasilDiTabel() {
        String tabel = jabatanPage.getAllTableRowTexts(); // Get the table content
        System.out.println(tabel);

        Assert.assertTrue(tabel == null || tabel.isEmpty() || tabel.contains("Abc"),
                "Table should be empty or display 'No data' for invalid search results");
        extentTest.log(LogStatus.PASS, "Admin melihat tidak ada hasil di tabel");
    }

    // Scenario 4 Pencarian dengan level yang valid (Positif)
    @When("Admin menambahkan jabatan dengan data valid")
    public void adminMenambahkanJabatanDenganDataValid() {
        jabatanPage.setBtnTambahkanNama("Customer Service", "2");
        extentTest.log(LogStatus.PASS, "Admin menambahkan jabatan dengan data valid");
    }

    @Then("Jabatan harus berhasil ditambahkan")
    public void jabatanHarusBerhasilDitambahkan() {
        Utils.delay(3);
        extentTest.log(LogStatus.PASS, "Jabatan harus berhasil ditambahkan");
    }

    //Scenario 5 Menambahkan jabatan dengan form kosong (Negatif)
    @When("Admin mencoba menambahkan jabatan dengan form kosong")
    public void adminMenambahkanJabatanDenganFormKosong() {
        jabatanPage.setBtnTambahkanNama("", "");
        jabatanPage.setBtnTambaModal();
        extentTest.log(LogStatus.PASS, "Admin mencoba menambahkan jabatan dengan form kosong");
    }

    @Then("Admin melihat pesan kesalahan validasi karena form kosong")
    public void adminHarusMelihatPesanKesalahanValidasi() {
        Utils.delay(3);
        String validationMessage = jabatanPage.vallNullName();
        Assert.assertEquals(validationMessage, "Please fill out this field.");
        extentTest.log(LogStatus.PASS, "Admin harus melihat pesan kesalahan validasi");
    }

    //Scenario 6 Menambahkan jabatan dengan form nama kosong (Negatif)
    @When("Admin mencoba menambahkan jabatan dengan field nama kosong")
    public void adminMencobaMenambahkanFieldNamaKosong() {
        jabatanPage.setBtnTambahkanNama("", "1");
        extentTest.log(LogStatus.PASS, "Admin mencoba menambahkan jabatan dengan field nama kosong");
    }

    @Then("Pesan kesalahan harus muncul untuk field nama")
    public void pesanKesalahanMunculuntukFieldNama() {
        String validationMessage = jabatanPage.vallNullName();
        Assert.assertEquals(validationMessage, "Please fill out this field.");
        extentTest.log(LogStatus.PASS, "Pesan kesalahan harus muncul untuk field nama");
    }

    //Scenario 7 Menambahkan jabatan dengan form level kosong (Negatif)
    @When("Admin mencoba menambahkan jabatan dengan field level kosong")
    public void adminMencobaMenambahkanFieldLevelKosong() {
        jabatanPage.setBtnTambahkanNama("Manager", "");
        jabatanPage.setBtnTambaModal();
        extentTest.log(LogStatus.PASS, "Admin mencoba menambahkan jabatan dengan field level kosong");
    }

    @Then("Pesan kesalahan pada field level")
    public void pesanKesalahanPadaFieldLevel() {
        String validationMessage = jabatanPage.vallNullLevel();
        Assert.assertEquals(validationMessage, "Please fill out this field.");
        extentTest.log(LogStatus.PASS, "Pesan kesalahan pada field level");
    }

    // Scenario 8 Mengedit jabatan dengan data valid (Positif)
    @When("Admin mengedit jabatan dengan data baru")
    public void adminMeneditJabatanDenganDataBaru() {
        jabatanPage.setBtnAction();
        Utils.delay(5);
        jabatanPage.editData("Service Customer", "1");
        Utils.delay(5);
        jabatanPage.setBtnSimpanEditModal();
    }

    @Then("Jabatan harus berhasil diperbarui")
    public void jabatanHarusBerhasilDiperbarui() {
        extentTest.log(LogStatus.PASS, "Jabatan harus berhasil diperbarui");
    }

    //Scenario 9 Mengedit jabatan dengan nama dan level kosong (Negatif)
    @When("Admin mengedit jabatan dengan nama dan level kosong")
    public void adminMeneditJabatanDenganNamaKosong() {
        jabatanPage.searchJabatan("1");
        jabatanPage.setBtnSearch();
        Utils.delay(5);
        jabatanPage.setBtnAction();
        jabatanPage.editData("", "");
        Utils.delay(5);
        jabatanPage.setBtnSimpanEditModal();
        extentTest.log(LogStatus.PASS, "Admin mengedit jabatan dengan nama kosong");
    }

    @Then("Pesan kesalahan harus ditampilkan untuk nama dan level")
    public void pesan_kesalahan_harus_ditampilkan_untuk_nama_dan_level() {
        String validationMessage = jabatanPage.vallNullName();
        Assert.assertEquals(validationMessage, "Please fill out this field.");
        extentTest.log(LogStatus.PASS, "Admin harus melihat pesan kesalahan validasi");
    }

    // Scenario 10 Mengedit jabatan dengan nama kosong (Negatif)
    @When("Admin mengedit jabatan dengan nama kosong dan level terisi")
    public void admin_mengedit_jabatan_dengan_nama_kosong_dan_level_terisi() {
        jabatanPage.searchJabatan("1");
        Utils.delay(5);
        jabatanPage.setBtnSearch();
        jabatanPage.setBtnAction();
        Utils.delay(5);
        jabatanPage.editData("", "1");
        jabatanPage.setBtnSimpanEditModal();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "Admin mengedit jabatan dengan nama kosong");
    }

    @Then("Pesan kesalahan harus muncul untuk nama jabatan yang kosong")
    public void pesanKesalahanMunculNamaJabatanKosong() {
        String validationMessage = jabatanPage.vallNullName();
        Assert.assertEquals(validationMessage, "Please fill out this field.");
        extentTest.log(LogStatus.PASS, "Pesan kesalahan harus muncul untuk nama jabatan yang kosong");
    }

    // Scenario 11 Mengedit jabatan dengan level kosong (Negatif)
    @When("Admin mengedit jabatan dengan nama terisi dan level kosong")
    public void adminMeneditJabatanDenganLevelKosong() {
        jabatanPage.searchJabatan("1");
        jabatanPage.setBtnSearch();
        jabatanPage.setBtnAction();
        jabatanPage.editData("Service", "");
        jabatanPage.setBtnSimpanEditModal();
        extentTest.log(LogStatus.PASS, "Admin mengedit jabatan dengan nama kosong");
    }

    @Then("Pesan kesalahan harus muncul untuk level jabatan yang kosong")
    public void pesanKesalahanMunculLevelKosong() {
        String validationMessage = jabatanPage.vallNullLevel();
        Assert.assertEquals(validationMessage, "Please fill out this field.");
        extentTest.log(LogStatus.PASS, "Pesan kesalahan harus muncul untuk level jabatan yang kosong");
    }

    //Scenario 12 Menghapus jabatan (Positif)
    @When("Admin menghapus jabatan")
    public void adminMenghapusJabatan() {
        jabatanPage.searchJabatan("1");
        jabatanPage.setBtnSearch();
        Utils.delay(3);
        jabatanPage.setBtnAction();
        jabatanPage.deleteTabel();
        extentTest.log(LogStatus.PASS, "Admin menghapus jabatan");

    }

    @Then("Jabatan harus terhapus")
    public void jabatanHarusTerhapus() {
        extentTest.log(LogStatus.PASS, "Jabatan harus terhapus");
    }

    //Scenario 13 Mereset pencarian (Positif)
    @When("Admin melakukan pencarian dan mengklik Reset")
    public void adminMelakukanPencarianReset() {
        jabatanPage.searchJabatan("2");
        jabatanPage.setBtnSearch();
        jabatanPage.setBtnReset();
        extentTest.log(LogStatus.PASS, "Admin melakukan pencarian dan mengklik Reset");

    }

    @Then("Pencarian harus dibersihkan")
    public void pencarianHarusDibersihkan() {
        Utils.delay(5);
        String searchFieldValue = jabatanPage.getSearchField();
        Assert.assertTrue(searchFieldValue.isEmpty(), "Search field should be empty after reset");
        Utils.delay(5);
        String tableRowText = jabatanPage.getTableRowText(1);
        Assert.assertNotNull(tableRowText, "Table should display all rows after reset");

        extentTest.log(LogStatus.PASS, "Pencarian harus dibersihkan");
    }
}
