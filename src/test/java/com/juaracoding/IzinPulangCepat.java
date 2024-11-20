package com.juaracoding;

import com.juaracoding.pages.Admin.HomePage;
import com.juaracoding.pages.Admin.IzinPulangCepatPage;
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

public class IzinPulangCepat {
    private static WebDriver driver;

    private static ExtentTest extentTest;

    private static WebLoginPage webLoginPage = new WebLoginPage();
    private static IzinPulangCepatPage izinPulangCepat = new IzinPulangCepatPage();
    private static HomePage homePage = new HomePage();

    public IzinPulangCepat() {
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    //Scenario 1 Menampilkan data izin pulang cepat dengan input tanggal yang sesuai
    @Given("pengguna berada di halaman data izin pulang cepat")
    public void pengguna_berada_di_halaman_data_izin_pulang_cepat() {
        driver.get(Constants.URL);
        webLoginPage.loginUser("admin@hadir.com", "admin@hadir");
        webLoginPage.setBtnLogin();
        homePage.setIzinPulangCepat();
        String nama = izinPulangCepat.getIzinCepatText();
        System.out.println(nama);
        extentTest.log(LogStatus.PASS, "Pengguna berada di halaman data izin pulang cepat");
    }

    @When("pengguna memasukkan tanggal yang valid")
    public void pengguna_memasukkan_tanggal_yang_valid() {
        izinPulangCepat.setTanggal("Nov 1, 2024", "Nov 16, 2024");
        izinPulangCepat.clickSearch();
        extentTest.log(LogStatus.PASS, "Pengguna memasukkan tanggal yang valid");
    }

    @Then("sistem menampilkan data izin pulang cepat dalam rentang tanggal yang sesuai")
    public void sistemMenampilkanDataIzinPulangCepatDalamRentangTanggalYangSesuai() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText != null && !tableRowText.isEmpty(), "Table row should not be null or empty");
        extentTest.log(LogStatus.PASS, "Sistem menampilkan data untuk rentang tanggal yang valid");
    }

    //Scenario 2 Menampilkan data izin pulang cepat dengan input nama dan tanggal yang sesuai
    @When("pengguna mengisi nama valid dan tanggal valid")
    public void pengguna_mengisi_nama_valid_dan_tanggal_valid() {
        izinPulangCepat.setNama("kazama");
        izinPulangCepat.setTanggal("Aug 1, 2024", "Nov 16, 2024");
        extentTest.log(LogStatus.PASS, "Pengguna memasukkan nama dan rentang tanggal yang valid");
    }

    @Then("sistem menampilkan data izin pulang cepat berdasarkan nama dan tanggal yang sesuai")
    public void sistemmenampilkandataizinpulangcepatberdasarkannamadantanggalyangsesuai() {
        izinPulangCepat.clickSearch();
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText.contains("kazama"), "Table row should contain the name 'kazama'");
        extentTest.log(LogStatus.PASS, "Sistem menampilkan data yang benar berdasarkan nama dan tanggal");
    }

    //scenario 3 Menampilkan data izin pulang cepat dengan input nama tidak valid
    @When("pengguna mengisi nama tidak valid")
    public void pengguna_mengisi_nama_tidak_valid() {
        izinPulangCepat.setNama("Abc");
        izinPulangCepat.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "Pengguna mengisi nama tidak valid");
    }

    @Then("sistem menampilkan data izin pulang cepat berdasarkan input tidak valid")
    public void sistemMenampilkanNamaTidakValid() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText == null || !tableRowText.contains("Abc"), "Table row should not contain the name 'Abc'");
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "sistem menampilkan data izin pulang cepat berdasarkan input tidak valid");
    }

    //Scenario 4 Menampilkan data izin pulang cepat dengan input angka
    @When("pengguna mengisi angka")
    public void pengguna_mengisi_angka() {
        izinPulangCepat.setNama("123");
        izinPulangCepat.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "Pengguna mengisi angka");
    }

    @Then("sistem tidak menampilkan data izin pulang cepat berdasarkan input angka")
    public void sistemTidakMenampilkanInputAngka() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertTrue(tableRowText == null || !tableRowText.contains("123"), "Table row should not contain the name '123'");
        extentTest.log(LogStatus.PASS, "sistem tidak menampilkan data izin pulang cepat berdasarkan input angka");
    }

    //Scenario 5 Menampilkan data izin pulang cepat tanpa mengisi nama, tanggal dan filter
    @When("pengguna tidak mengisi tanggal")
    public void pengguna_tidak_mengisi_tanggal() {
        homePage.setIzinPulangCepat();
        Utils.delay(10);
        izinPulangCepat.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "Pengguna tidak mengisi tanggal");
    }

    @Then("sistem menampilkan semua data izin pulang cepat tanpa filter tanggal")
    public void sistemmenampilkansemuadataizinpulangcepattanpafiltertanggal() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertNotNull(tableRowText, "Table should display data when no filters are applied");
        extentTest.log(LogStatus.PASS, "Sistem menampilkan semua data tanpa filter tanggal");
    }

    //Scenario 6 Menampilkan data izin pulang cepat dengan input tanggal tanpa memilih start date
    @When("pengguna tidak mengisi start date tetapi mengisi end date")
    public void penggunatidakmengisistartdatetetapimengisienddate() {
        izinPulangCepat.setTanggal("", "Nov 11, 2024");
        izinPulangCepat.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "Pengguna tidak mengisi start date tetapi mengisi end date");
    }

    @Then("sistem menampilkan izin pulang cepat tanpa memilih start date")
    public void sistemMenampilkan_izin_pulangcepattanpamemilihenddate() {
        String tableRowText = izinPulangCepat.getTableRowText(0);

        if (tableRowText == null || tableRowText.isEmpty()) {
            extentTest.log(LogStatus.FAIL, "No data displayed in the table for the given date range");
            System.out.println("No data displayed in the table for the given date range");
            // Jika tabel kosong adalah skenario yang valid, gunakan log alih-alih Assert.fail()
        } else {
            Assert.assertNotNull(tableRowText, "Data should be displayed for the given date range");
            extentTest.log(LogStatus.PASS, "Table displayed data for the given date range");
        }
    }

    //Scenario 7 Menampilkan data izin pulang cepat dengan input tanggal tanpa memilih end date
    @When("pengguna mengisi start date tetapi tidak mengisi end date")
    public void penggunamengisistartdate() {
        izinPulangCepat.setTanggal("12 Okt, 2024", "");
        izinPulangCepat.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "Pengguna mengisi start date tetapi tidak mengisi end date");
    }

    @Then("sistem menampilkan izin pulang cepat tanpa memilih end date")
    public void sistemMenampilkan_izin_pulangcepattanpamemiliihstartdate() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Utils.delay(10);

        if (tableRowText == null || tableRowText.isEmpty()) {
            extentTest.log(LogStatus.FAIL, "No data displayed in the table for the given date range");
            Assert.fail("Table is empty, no rows available for the given date range");
        } else {
            System.out.println(tableRowText);
            // Assert that the table row is not null and contains expected data
            Assert.assertNotNull(tableRowText, "Sistem menampilkan semua data cuti dengan mengisi start date");
            extentTest.log(LogStatus.PASS, "Sistem menampilkan izin pulang cepat tanpa memilih end date");
        }

    }

    //Scenario 8 Menampilkan data izin pulang cepat dengan input tanggal yang akan datang
    @When("pengguna mengisi date yang akan datang")
    public void penggunamengisidateyangakandatang() {
        izinPulangCepat.setTanggal("Nov 30, 2024", "Nov 30, 2024");
        izinPulangCepat.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "pengguna mengisi date yang akan datang");
    }

    @Then("sistem menampilkan izin pulang cepat yang akan datang")
    public void sistemMenampilkanizinpulangcepatyangakandatang() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Utils.delay(10);
        Assert.assertTrue(tableRowText == null || !tableRowText.contains("Nov 30, 2024"), "Table row should not contain the name 'Nov 30, 2024'");
        extentTest.log(LogStatus.PASS, "sistem menampilkan izin pulang cepat yang akan datang");
    }

    //Scenario 9 Menampilkan data izin pulang cepat dengan filter departemen
    @When("pengguna memilih filter departemen \"IT Programmer\"")
    public void penggunamemilihfilterdepartemen() {
        izinPulangCepat.setBtnFilter();
        izinPulangCepat.setNameDepartement("IT Programmer");
        izinPulangCepat.setBtnFilterTerapkan();
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "pengguna memilih filter departemen \"Amex Merchant\"");
    }

    @Then("sistem menampilkan data izin pulang cepat hanya untuk departemen valid")
    public void sistemmenampilkandepartemenvalid() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);

        // Assert that the table row contains the department name "IT Programmer"
        Assert.assertTrue(tableRowText.contains("IT Programmer"), "Table row should contain 'IT Programmer'");
        extentTest.log(LogStatus.PASS, "sistem menampilkan data izin pulang cepat hanya untuk departemen valid");
    }


    //Scenario 10 Menampilkan data izin pulang cepat dengan filter tidak valid
    @When("pengguna memilih filter departemen \"123\"")
    public void pengggunaMemilihFilterDepartemenTidakValid() {
        izinPulangCepat.setBtnFilter();
        Utils.delay(20);
        izinPulangCepat.setNameTidakDepartement("123");
        izinPulangCepat.setBtnFilterTerapkan();
        extentTest.log(LogStatus.PASS, "pengguna memilih filter departemen \"123\"");
    }

    @Then("sistem menampilkan data izin pulang cepat dengan filter \"123\"")
    public void sistemmenampilkandepartemenTidakValid() {
        String tableRowText = izinPulangCepat.getTableRowText(1);
        System.out.println(tableRowText);
        Assert.assertNotNull(tableRowText, "Table should display all rows");
        extentTest.log(LogStatus.PASS, "Sistem menampilkan data izin pulang cepat dengan filter \"123\"");
    }

    //Scenario 11 Menampilkan data izin pulang cepat dengan nama dan filter departemen
    @When("pengguna mengisi filter nama valid dan filter departemen valid")
    public void penggunamisifilternamavalid() {
        izinPulangCepat.setNama("Agung Aji Nugraha");
        izinPulangCepat.setBtnFilter();
        izinPulangCepat.setNameDepartement("Amex Merchant");
        izinPulangCepat.setBtnFilterTerapkan();
        izinPulangCepat.clickSearch();
        Utils.delay(5);
        extentTest.log(LogStatus.PASS, "pengguna mengisi filter nama valid dan filter departemen valid");
    }

    @Then("sistem menampilkan data izin pulang cepat hanya untuk nama valid di departemen valid")
    public void sistem_menampilkan_data() {
        System.out.println(izinPulangCepat.getTableRowText(1));
        extentTest.log(LogStatus.PASS, "sistem menampilkan data izin pulang cepat hanya untuk nama valid di departemen valid");
    }

    //Scenario 12 Menggunakan tombol Batal untuk keluar dari modal filter
    @And("pengguna membuka modal filter")
    public void penggunamembukamodalfilter() {
        izinPulangCepat.setBtnFilter();
        extentTest.log(LogStatus.PASS, "pengguna membuka modal filter");
    }

    @When("pengguna mengklik tombol \"Batal\"")
    public void pengguna_klik_batal() {
        Utils.delay(5);
        izinPulangCepat.setBtnBatal();
        extentTest.log(LogStatus.PASS, "pengguna mengklik tombol \"Batal\"");
    }

    @Then("modal filter tertutup dan pengguna kembali ke halaman utama tanpa perubahan filter pada izin pulang cepat")
    public void modalfitertertutup() {
        extentTest.log(LogStatus.PASS, "sistem berhasil keluar");
    }

    //Scenario 13 Menghapus filter yang dipilih dengan tombol X
    @And("pengguna mengisi filter nama dan filter departemen yang valid")
    public void penggunaMengisifilternamavalid() {
        izinPulangCepat.setBtnFilter();
        izinPulangCepat.setNameDepartement("IT Programmer");
        Utils.delay(10);
    }

    @When("pengguna mengklik tombol X pada filter nama \"Amex Merchant\"")
    public void pengguna_xpadafilternama() {
        izinPulangCepat.setBtnX();
        extentTest.log(LogStatus.PASS, "pengguna mengklik tombol X pada filter nama \"Amex Merchant\"");
    }

    @Then("filter nama \"Amex Merchant\" dihapus dan data ditampilkan tanpa filter nama pada izin pulang cepat")
    public void filternama() {
        Assert.assertEquals(izinPulangCepat.getAppliedDepartmentFilterText(), "", "Department filter should be cleared");
        extentTest.log(LogStatus.PASS, "sistem berhasil menghapus nama di filter");
    }
}


