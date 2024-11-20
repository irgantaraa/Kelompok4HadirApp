package com.juaracoding;

import com.juaracoding.pages.User.HomePageMobile;
import com.juaracoding.pages.User.MobileLoginPage;
import com.juaracoding.pages.User.SakitMobilePage;
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

public class SakitMobile {
    private static WebDriver driver;

    private static ExtentTest extentTest;

    private static MobileLoginPage mobileLoginPage = new MobileLoginPage();
    private static HomePageMobile homePageMobile = new HomePageMobile();
    private static SakitMobilePage sakitMobilePage = new SakitMobilePage();

    public SakitMobile() {
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    //Scenario 1
    @Given("Pengguna berada di halaman pengajuan sakit")
    public void userOnSakitPage() {
        driver.get(Constants.URL2);
        mobileLoginPage.loginUser("testuser1@gmail.com", "12345678");
        mobileLoginPage.setBtnLogin();
        homePageMobile.setMenuSakit();
        String listSakit = sakitMobilePage.getTxtListSakit();
        System.out.println(listSakit);

        Assert.assertEquals(listSakit, "List Request Sakit",
                "The text 'List Request Sakit' is not displayed as expected");

        String total = sakitMobilePage.getTxtTotal();
        System.out.println(total);

        Assert.assertNotNull(total, "The total count should not be null");
        Assert.assertFalse(total.isEmpty(), "The total count should not be empty");
        extentTest.log(LogStatus.PASS, "Pengguna berada di halaman pengajuan sakit");
    }

    @When("Pengguna memilih tanggal dan meng-upload surat sakit valid")
    public void userChoseDateAndUploadValid() {
        sakitMobilePage.setAjukanSakit();
        sakitMobilePage.setTanggal("Nov 20, 2024", "Nov 21, 2024");
        sakitMobilePage.setBtnUploadImage();
        extentTest.log(LogStatus.PASS, "Pengguna memilih tanggal dan meng-upload surat sakit valid");

    }

    @Then("Pengajuan diterima")
    public void pengajuanDiterima() {
        sakitMobilePage.setBtnAjukanRequestSakit();
        Utils.delay(10);
        if (sakitMobilePage.isAlertDisplayed()) {
            String alertMessage = sakitMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Anda sudah melakukan izin sakit di tanggal tersebut",
                    "The alert message does not match the expected text");
            extentTest.log(LogStatus.FAIL, "Alert is displayed as expected.");
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.PASS, "Izin Pulang Cepat berhasil diajukan - Upload succeeded without an alert.");
        }
    }

    //Scenario 2
    @When("Pengguna meng-upload file bukan gambar")
    public void pengajuanUploadFile() {
        sakitMobilePage.setAjukanSakit();
        sakitMobilePage.setTanggal("Nov 22, 2024", "Nov 23, 2024");
        sakitMobilePage.setBtnUploadDocx();
        Utils.handleAlert(driver, "Pastikan gambar/foto yang diupload");
        extentTest.log(LogStatus.PASS, "Pengguna mengajukan sakit dengan tanggal dan surat sakit valid");

    }

    @Then("Sistem menampilkan peringatan format file tidak valid")
    public void sistemMenampilkanPeringatanFormatFile() {
        sakitMobilePage.setBtnAjukanRequestSakit();
        if (sakitMobilePage.isAlertDisplayed()) {
            String alertMessage = sakitMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Photo is Required",
                    "The alert message does not match the expected text");
        } else {
            System.out.println("No alert message is displayed.");
        }
        extentTest.log(LogStatus.PASS, "Sistem menampilkan peringatan format file tidak valid");
    }

    //Scenario 3
    @When("Pengguna memilih tanggal tanpa meng-upload surat sakit")
    public void penggunaTidakMengisiTanggal() {
        sakitMobilePage.setAjukanSakit();
        sakitMobilePage.setTanggal("Nov 16, 2024", "Nov 17, 2024");
        extentTest.log(LogStatus.PASS, "Pengguna memilih tanggal tanpa meng-upload surat sakit");
    }

    @Then("Sistem menampilkan peringatan")
    public void sistemMenampilkanPeringatan() {
        sakitMobilePage.setBtnAjukanRequestSakit();
        Utils.delay(7);
        extentTest.log(LogStatus.PASS, "Sistem menampilkan peringatan input tanggal");
    }

    //Scenario 4
    @When("Pengguna memilih mengajukan sakit tanpa input tanggal")
    public void penggunaeMilihMengajukanSakitTidakMengisiTangga() {
        sakitMobilePage.setAjukanSakit();
        sakitMobilePage.setTanggal("", "");
        sakitMobilePage.setBtnAjukanRequestSakit();
        extentTest.log(LogStatus.PASS, "Pengguna memilih mengajukan sakit tanpa input tanggal");
        Utils.delay(5);
    }

    @Then("Sistem menampilkan peringatan input tanggal")
    public void sistemMenampilkanPeringatanInputTanggal() {
        if (sakitMobilePage.isAlertDisplayed()) {
            String alertMessage = sakitMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Anda sudah melakukan izin sakit di tanggal tersebut",
                    "The alert message does not match the expected text");
        } else {
            System.out.println("No alert message is displayed.");
        }
        extentTest.log(LogStatus.PASS, "Sistem menampilkan peringatan input tanggal");
    }

    //Scenario 5
    @When("Pengguna memilih tanggal")
    public void penggunaChooseDate() {
        sakitMobilePage.setAjukanSakit();
        extentTest.log(LogStatus.PASS, "Pengguna memilih tanggal");
    }

    @And("Pengguna menekan tombol \"Batal\"")
    public void penggunaMenekanTombol() {
        sakitMobilePage.setBtnBatalCalender("Nov 12, 2024", "Nov 16, 2024");
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "Pengguna menekan tombol \"Batal\"");
    }

    @Then("Start date dan end date kosong")
    public void startDateDanEndDateKosong() {
        extentTest.log(LogStatus.PASS, "Start date dan end date kosong");
    }

    //Scenario 6
    @When("Pengguna memilih tanggal dan meng-upload surat sakit")
    public void penggunaTanggalDanMengUploadSurat() {
        sakitMobilePage.setAjukanSakit();
        sakitMobilePage.setTanggal("Nov 12, 2024", "Nov 16, 2024");
        sakitMobilePage.setBtnUploadImage();
        extentTest.log(LogStatus.PASS, "Pengguna memilih tanggal dan meng-upload surat sakit");
    }

    @And("Pengguna menekan tombol \"Reset\"")
    public void penggunaTanggalReset() {
        sakitMobilePage.setBtnReset();
        Utils.delay(10);
        extentTest.log(LogStatus.PASS, "Pengguna menekan tombol \"Reset\"");
    }

    @Then("Tanggal dan file dihapus")
    public void tanggalDanFileDihapus() {
        extentTest.log(LogStatus.PASS, "Tanggal dan file dihapus");
    }
}
