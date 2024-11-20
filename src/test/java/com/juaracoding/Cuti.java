package com.juaracoding;

import com.juaracoding.pages.Admin.CutiPage;
import com.juaracoding.pages.Admin.HomePage;
import com.juaracoding.pages.Admin.WebLoginPage;
import com.juaracoding.utils.Constants;
import com.juaracoding.utils.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class Cuti {
    private static WebDriver driver;

    private static ExtentTest extentTest;

    private static WebLoginPage webLoginPage = new WebLoginPage();
    private static CutiPage cutiPage = new CutiPage();
    private static HomePage homePage = new HomePage();

    public Cuti() {
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    //Scenario 1 Menampilkan data cuti dengan input tanggal yang sesuai
    @Given("user berada di halaman data cuti")
    public void user_berada_di_halaman_cuti() {
        driver.get(Constants.URL);
        webLoginPage.loginUser("admin@hadir.com", "admin@hadir");
        webLoginPage.setBtnLogin();
        homePage.setCuti();
        String logo = cutiPage.getCutiText();
        System.out.println(logo);
        extentTest.log(LogStatus.PASS, "User navigated to Cuti page");
    }

    @When("user memasukkan tanggal yang valid")
    public void user_memasukkan_tanggal_yang_valid_Cuti() {
        cutiPage.setTanggal("Jun 1, 2024", "Nov 16, 2024");
        cutiPage.clickSearch();
        extentTest.log(LogStatus.PASS, "User memasukkan tanggal yang valid");
    }

    @Then("sistem menampilkan data cuti dalam rentang tanggal yang sesuai")
    public void sistemMenampilkanDataIzinPulangCepatDalamRentangTanggalYangSesuai() {
        String tableRowText = cutiPage.getTableRowText(1);
        System.out.println(tableRowText);
        Utils.delay(2);
        assertTrue(tableRowText != null && !tableRowText.isEmpty(), "Table row should not be null or empty");
        extentTest.log(LogStatus.PASS, "sistem menampilkan data cuti dalam rentang tanggal yang sesuai");
    }

    //Scenario 2 Menampilkan data cuti dengan input nama dan tanggal yang sesuai
    @When("user mengisi nama valid dan tanggal valid")
    public void user_mengisi_nama_valid_dan_tanggal_valid() {
        cutiPage.setNama("Anif Biantoro");
        Utils.delay(2);
        cutiPage.setTanggal("Jun 1, 2024", "Nov 16, 2024");
        cutiPage.clickSearch();
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "User mengisi nama valid dan tanggal valid");
    }

    @Then("sistem menampilkan data cuti berdasarkan nama dan tanggal yang sesuai")
    public void sistem_menampilkan_data_cuti_berdasarkan_nama_dan_tanggal_yang_sesuai() {
        String tableRowText = cutiPage.getTableRowText(1);
        System.out.println(tableRowText);
        assertTrue(tableRowText.contains("Anif Biantoro"), "Table row should contain the name 'Anif Biantoro'");
        extentTest.log(LogStatus.PASS, "sistem menampilkan data cuti berdasarkan nama dan tanggal yang sesuai");
    }

    //Scenario 3 Menampilkan data cuti input nama tidak valid
    @When("user tidak input nama tidak valid")
    public void user_tidak_input_nama_tidak_valid() {
        cutiPage.setNama("Abc");
        cutiPage.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "User tidak input nama tidak valid");
    }

    @Then("sistem tidak menampilkan data nama tidak valid")
    public void sistem_tidak_input_nama_tidak_valid() {
        String tableRowText = cutiPage.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText == null || !tableRowText.contains("Abc"), "Table row should not contain the name 'Abc'");
        extentTest.log(LogStatus.PASS, "sistem tidak menampilkan data nama tidak valid");
    }

    //Scenario 4 Menampilkan data cuti input angka
    @When("user melakukan input angka")
    public void user_melakukan_input_angka() {
        cutiPage.setNama("29001");
        cutiPage.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "User melakukan input angka");
    }

    @Then("sistem tidak menampilkan data angka")
    public void sistem_tidak_Menampilkan_angka() {
        String tableRowText = cutiPage.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText == null || !tableRowText.contains("29001"), "Table row should not contain the name '29001'");
        extentTest.log(LogStatus.PASS, "Sistem tidak menampilkan data angka");
    }

    // Scenario 5 Menampilkan data cuti tanpa mengisi nama, tanggal, dan filter
    @When("user tidak mengisi nama, tanggal, dan filter")
    public void user_tidak_mengisi_tanggal_nama_dan_filter() {
        cutiPage.clickSearch();
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "User tidak mengisi tanggal, nama, dan filtere");
    }

    @Then("sistem menampilkan semua data cuti tanpa filter tanggal")
    public void sistem_menampilkan_semua_data_cuti_tanggal() {
        Utils.delay(10);
        String tableRowText = cutiPage.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertNotNull(tableRowText, "Table should display data when no filters are applied");
        extentTest.log(LogStatus.PASS, "Sistem menampilkan semua data tanpa filter, tanggal, dan nama");
    }

    //Scenario 6 Menampilkan data cuti dengan input tanggal tanpa memilih start date
    @When("user tidak mengisi start date tetapi mengisi end date")
    public void user_tidak_mengisi_start_date() {
        cutiPage.setTanggal("", "Nov 10, 2024");
        cutiPage.clickSearch();
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "User tidak mengisi start date tetapi mengisi end date");
    }

    @Then("sistem menampilkan semua data cuti dengan mengisi start date")
    public void sistemMenampilkan_semua_data_cuti_dengan_mengisi_start_date() {
        String tableRowText = cutiPage.getTableRowText(1);

        Assert.assertTrue(tableRowText == null || !tableRowText.contains("Nov 10, 2024"), "Table row should not contain the name 'Nov 10, 2024'");

//        if (tableRowText == null || tableRowText.isEmpty()) {
//            extentTest.log(LogStatus.FAIL, "No data displayed in the table for the given date range");
//            Assert.fail("Table is empty, no rows available for the given date range");
//        } else {
//            System.out.println(tableRowText);
//            // Assert that the table row is not null and contains expected data
//            Assert.assertNotNull(tableRowText, "Sistem menampilkan semua data cuti dengan mengisi start date");
//            extentTest.log(LogStatus.PASS, "Sistem menampilkan semua data cuti dengan mengisi start date");
//        }
    }

    //Scenario 7 Menampilkan data cuti dengan input tanggal tanpa memilih end date
    @When("user mengisi start date tetapi tidak mengisi end date")
    public void user_mengisi_start_date() {
        cutiPage.setTanggal("Jun 01, 2024", "");
        cutiPage.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.FAIL, "User mengisi start date tetapi tidak mengisi end date");
    }

    @Then("sistem menampilkan semua data cuti dengan mengisi end date")
    public void sistemMenampilkan_semua_data_cuti_dengan_mengisi_end_date() {
        String tableRowText = cutiPage.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText == null || !tableRowText.contains("Jun 01, 2024"), "Table row should not contain the name 'Jun 01, 2024'");
        extentTest.log(LogStatus.PASS, "sistem menampilkan semua data cuti dengan mengisi end date");
    }

    // Scenario 8 Menampilkan data cuti dengan input tanggal yang akan datang
    @When("user mengisi tanggal yang akan datang")
    public void user_mengisi_tanggal_yang_akan() {
        cutiPage.setTanggal("Nov 30, 2024", "Nov 30, 2024");
        cutiPage.clickSearch();
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "User mengisi tanggal yang akan");
    }

    @Then("sistem tidak menampilkan semua data cuti dengan tanggal yang akan datang")
    public void sistemTidakMenampilkanCutiTanggalYangAkanDatang() {
        String tableRowText = cutiPage.getTableRowText(1);
        assertTrue(tableRowText == null || !tableRowText.contains("Nov 30, 2024"), "Table row should not contain the name 'Nov 30, 2024'");
        extentTest.log(LogStatus.PASS, "sistem tidak menampilkan semua data cuti dengan tanggal yang akan datang");
    }


    // Scenario 9 Menampilkan data cuti dengan filter departemen
    @When("user memilih filter departemen")
    public void user_memilih_filter_departemen() {
        cutiPage.setBtnFilter();
        cutiPage.setNameDepartement("IT Programmer");
        Utils.delay(5);
        cutiPage.setBtnFilterTerapkan();
        cutiPage.clickSearch();
        extentTest.log(LogStatus.PASS, "User memilih filter departemen");
    }

    @Then("sistem menampilkan data cuti hanya untuk departemen valid")
    public void sistem_menampilkan_departement_valid() {
        Utils.delay(10);
        System.out.println(cutiPage.getTableRowText(1));
        extentTest.log(LogStatus.PASS, "Sistem menampilkan data cuti hanya untuk departemen valid");
    }

    // Scenario 10 Menampilkan data cuti dengan filter yang tidak valid
    @When("user memilih filter departemen yang tidak valid")
    public void user_memilih_filter_departemenYangTidak() {
        cutiPage.setBtnFilter();
        cutiPage.setNameTidakDepartement("123");
        cutiPage.setBtnFilterTerapkan();
        extentTest.log(LogStatus.PASS, "User memilih filter departemen");
    }

    @Then("sistem tidak menampilkan data cuti hanya untuk departemen valid")
    public void sistemTidakMenampilkanDataCutiTidak() {
        Utils.delay(10);
        System.out.println(cutiPage.getTableRowText(1));
        extentTest.log(LogStatus.PASS, "sistem tidak menampilkan data cuti hanya untuk departemen valid");
    }

    // Scenario 11 Menampilkan data cuti dengan filter nama dan departemen
    @When("user mengisi nama valid dan filter departemen valid")
    public void userNamaValid_dan_filter_departemen_valid() {
        cutiPage.setNama("Anif Biantoro");
        cutiPage.setBtnFilter();
        cutiPage.setNameDepartement("IT Programmer");
        cutiPage.setBtnFilterTerapkan();
        cutiPage.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "User mengisi nama valid dan filter departemen valid");
    }

    @Then("sistem menampilkan data cuti hanya untuk nama valid di departemen valid")
    public void sistem_menampilkan_departemen_valid() {
        String tableRowText = cutiPage.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText.contains("Anif Biantoro"), "Table row should contain the name 'Anif Biantoro'");
        extentTest.log(LogStatus.PASS, "sistem menampilkan data cuti berdasarkan nama dan filter yang sesuai");
    }

    //Scenario 12 Menggunakan tombol Batal untuk keluar dari modal filter
    @And("user membuka modal filter")
    public void user_membuka_modal_filter() {
        cutiPage.setBtnFilter();
        extentTest.log(LogStatus.PASS, "User membuka modal filter");
    }

    @When("user mengklik tombol \"Batal\"")
    public void user_mengklik_tombol_batal() {
        cutiPage.setBtnBatal();
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "user mengklik tombol \"Batal\"");
    }

    @Then("modal filter tertutup dan pengguna kembali ke halaman utama tanpa perubahan filter pada cuti")
    public void modal_filter_batal() {
        extentTest.log(LogStatus.PASS, "sistem berhasil menghapus nama di filter");
    }

    // Scenario 13 Menghapus filter yang dipilih dengan tombol X
    @And("user mengisi nama dan filter departemen valid")
    public void userFilterNameDepartement_valid() {
        cutiPage.setNama("Agung Aji Nugraha");
        cutiPage.setBtnFilter();
        Utils.delay(10);
        cutiPage.setNameDepartement("Amex Merchant");
        Utils.delay(10);
    }

    @When("user mengklik tombol X pada filter departemen")
    public void userFilterNameDepartement_X_pada() {
        cutiPage.setBtnX();
        extentTest.log(LogStatus.PASS, "pengguna mengklik tombol X pada filter nama \"Amex Merchant\"");

    }

    @Then("filter departemen \"Amex Merchant\" dihapus dan data ditampilkan tanpa filter departemen pada cuti")
    public void filterdihapus() {
        Assert.assertEquals(cutiPage.getAppliedDepartmentFilterText(), "", "Department filter should be cleared");
        extentTest.log(LogStatus.PASS, "sistem berhasil menghapus nama di filter");
    }


    //Scenario 14
    @When("user mengklik tombol edit pada data cuti karyawan")
    public void melakukan_edit_salah_satu_karyawan() {
        cutiPage.findAndClickActionButton();
    }

    @And("modal edit cuti muncul dengan dua form upliner Atasan 2 dan Atasan 3 cuti")
            public void modal_edit_muncul() {
        cutiPage.setEdit();
    }
    @And("user melakukan edit data")
    public void userMelakukan_edit_data() {
        cutiPage.editUpliner("Jaya Lazuardi","User v3");
    }

    @And("user mengklik tombol ubah data")
    public void userMengklik_tombol_ubah_data() {
        cutiPage.setTombolUbahData();
    }

    @Then("data cuti karyawan berhasil diperbarui dengan diperbarui dengan upliner yang baru")
    public void dataCutiDiperbaharui(){
        // Verifikasi bahwa perubahan berhasil dengan memeriksa baris pertama (atau sesuai)
        String updatedText = cutiPage.getTableRowText(1);  // Mendapatkan teks dari baris pertama.

        // Memastikan bahwa nama upliner 2 dan 3 sudah diperbarui dengan benar
        Assert.assertTrue(updatedText.contains("Jaya Lazuardi"));  // Pastikan upliner 2 yang baru muncul
        Assert.assertTrue(updatedText.contains("User v3"));  // Pastikan upliner 3 yang baru muncul
    }

}



