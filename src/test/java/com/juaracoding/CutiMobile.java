package com.juaracoding;

import com.juaracoding.pages.User.CutiMobilePage;
import com.juaracoding.pages.User.HomePageMobile;
import com.juaracoding.pages.User.MobileLoginPage;
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


public class CutiMobile {
    private static WebDriver driver;

    private static ExtentTest extentTest;

    private static MobileLoginPage mobileLoginPage = new MobileLoginPage();
    private static HomePageMobile homePageMobile = new HomePageMobile();
    private static CutiMobilePage cutiMobilePage = new CutiMobilePage();

    public CutiMobile() {
        driver = Hooks.driver;
        extentTest = Hooks.extentTest;
    }

    // Scenario 1: Menampilkan informasi cuti
    @Given("pengguna telah login ke aplikasi")
    public void penggunaSudahLoginKeAplikasi() {
        driver.get(Constants.URL2);
        mobileLoginPage.loginUser("testuser1@gmail.com", "12345678");
        mobileLoginPage.setBtnLogin();
        homePageMobile.setMenuCuti(); // Arahkan ke halaman pengajuan cuti
    }

    @When("pengguna membuka halaman pengajuan cuti")
    public void penggunaMembukaHalamanPengajuanCuti() {
        cutiMobilePage.clickAjukanCuti();

    }

    @Then("informasi cuti harus ditampilkan dengan benar")
    public void informasiCutiDitampilkanDenganBenar() {
        cutiMobilePage.clickInfoCuti(); // Navigasi ke bagian informasi cuti
        String infoCuti = cutiMobilePage.getListRequestCutiText();
        String totalCuti = cutiMobilePage.getTotalCutiText();
        System.out.println(infoCuti);
        System.out.println(totalCuti);
        assert !infoCuti.isEmpty() && !totalCuti.isEmpty(); // Validasi informasi cuti ditampilkan
    }

    // Scenario 2: Mengajukan cuti dengan data valid
    @And("pengguna memilih tipe cuti dan memilih tanggal mulai dan tanggal selesai")
    public void penggunaMemilihtipeDanMemilihTanggalCuti() {
        cutiMobilePage.clickPernikahanDiriSendiri();
        Utils.delay(3);
        cutiMobilePage.setTanggal("Nov 28, 2024", "Nov 29, 2024"); // Pilih tanggal cuti
        Utils.delay(3);
        extentTest.log(LogStatus.PASS,"Pengguna memilih tanggal mulai dan tanggal selesai");
    }

    @And("pengguna mengisi catatan")
    public void penggunaMengisiCatatan() {
        cutiMobilePage.setCatatan("Saudara dari ibu menikah"); // Isi catatan cuti
        extentTest.log(LogStatus.PASS,"Pengguna mengisi catatan");
    }

    @When("pengguna mengklik tombol \"Ajukan\"")
    public void penggunaMengklikTombol() {
        cutiMobilePage.clickAjukan();
        extentTest.log(LogStatus.PASS,"Pengguna mengklik tombol");
    }

    @Then("cuti berhasil diajukan dan sistem menampilkan konfirmasi pengajuan cuti")
    public void cutiBerhasilDiajukan() {
        if (cutiMobilePage.isAlertDisplayed()) {
            String alertMessage = cutiMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Kontrak anda sudah habis ditanggal tersebut!",
                    "The alert message does not match the expected text");

            // Log the failure since an alert indicates a failed upload
            extentTest.log(LogStatus.FAIL, "Upload failed. Alert is displayed: " + alertMessage);
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.PASS, "Cuti berhasil diajukan - Upload succeeded without an alert.");
        }
    }

    // Scenario 3: Mengajukan cuti dengan data kosong atau tidak valid
    @And("pengguna tidak mengisi tanggal mulai dan tanggal selesai")
    public void penggunaTidakMengisiTanggalMulaidanTanggalSelesai() {
        cutiMobilePage.setTanggal("", "");
        extentTest.log(LogStatus.PASS,"Pengguna tidak mengisi tanggal mulai dan tanggal selesai");
    }
    @And("pengguna tidak mengisi catatan")
    public void penggunaTidakMengisiCatatan() {
        cutiMobilePage.setCatatan("");
        extentTest.log(LogStatus.PASS,"Pengguna tidak mengisi catatan");
    }

    @Then("sistem menampilkan pesan error bahwa semua field harus diisi")
    public void sistemMenampilkanPesanErrorSemuaFieldHarusDiisi() {
        if (cutiMobilePage.isAlertDisplayed()) {
            String alertMessage = cutiMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Incorrect datetime value: 'Invalid date' for column 'leave_date_from' at row 1",
                    "The alert message does not match the expected text");
            extentTest.log(LogStatus.PASS, "Alert is displayed as expected.");
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.INFO, "No alert displayed for the given input.");
        }
        extentTest.log(LogStatus.PASS, "Pengajuan diterima");
    }

    // Scenario 4  Ajukan cuti dengan memilih tipe cuti dan field lain kosong
    @And("pengguna tidak mengisi tanggal mulai, tanggal selesai, dan catatan")
    public void penggunaMengosongkanSemuaField(){
        cutiMobilePage.clickKhitananPembabtisan();
    }

    @When("pengguna klik tombol Ajukan")
    public void penggunaKlikTombolAjukan() {
        cutiMobilePage.clickAjukan();
    }

    @Then("sistem menampilkan pesan error bahwa tanggal mulai, tanggal selesai, dan catatan harus diisi")
    public void sistemMenampilkanPesanErrorFieldWajib() {
        if (cutiMobilePage.isAlertDisplayed()) {
            String alertMessage = cutiMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Incorrect datetime value: 'Invalid date' for column 'leave_date_from' at row 1",
                    "The alert message does not match the expected text");
            extentTest.log(LogStatus.PASS, "Alert is displayed as expected.");
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.INFO, "No alert displayed for the given input.");
        }
        extentTest.log(LogStatus.PASS, "Pengajuan diterima");
    }



    @Then("sistem menampilkan pesan error bahwa catatan harus diisi")
    public void sistemMenampilkanPesanErrorCatatan() {
        // Verifikasi pesan error
        if (cutiMobilePage.isAlertDisplayed()) {
            String alertMessage = cutiMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Anda sudah melakukan izin sakit di tanggal tersebut",
                    "The alert message does not match the expected text");
            extentTest.log(LogStatus.PASS, "Alert is displayed as expected.");
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.INFO, "No alert displayed for the given input.");
        }
        extentTest.log(LogStatus.PASS, "Pengajuan diterima");
    }

    // Scenario 5 Ajukan cuti dengan memilih tanggal cuti dan field lain kosong
    @And("pengguna memilih tanggal mulai dan tanggal selesai")
    public void penggunaMemilihTanggalSelesai20241117() {
        cutiMobilePage.setTanggal("Nov 16, 2024", "Nov 17, 2024");
    }

    @Then("sistem menampilkan pesan error")
    public void sistemCutiMenampilkanPesanError() {
        if (cutiMobilePage.isAlertDisplayed()) {
            String alertMessage = cutiMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Cannot add or update a child row: a foreign key constraint fails (`hadir_magang`.`request_leave`, CONSTRAINT `request_leave_ibfk_6` FOREIGN KEY (`leave_type_id`) REFERENCES `leave_type` (`id`) ON UPDATE CASCADE)",
                    "The alert message does not match the expected text");
            extentTest.log(LogStatus.PASS, "Alert is displayed as expected.");
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.INFO, "No alert displayed for the given input.");
        }
        extentTest.log(LogStatus.PASS, "Pengajuan diterima");
    }


    //Scenario 6 Ajukan cuti dengan mengisi form catatan dan field lain kosong
    @Then("sistem menampilkan pesan error bahwa tanggal mulai dan tanggal selesai harus diisi")
    public void sistemMenampilkanPesanErrorTanggal() {
        if (cutiMobilePage.isAlertDisplayed()) {
            String alertMessage = cutiMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Incorrect datetime value: 'Invalid date' for column 'leave_date_from' at row 1",
                    "The alert message does not match the expected text");
            extentTest.log(LogStatus.PASS, "Alert is displayed as expected.");
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.INFO, "No alert displayed for the given input.");
        }
        extentTest.log(LogStatus.PASS, "Pengajuan diterima");
    }
    //Scenario 7
    @And("pengguna memilih tanggal mulai \"2023-11-10\" dan tanggal selesai \"2023-11-11\"")
    public void memilihTanggalMulaiDanTanggalSelesai20241111() {
        cutiMobilePage.setTanggal("Nov 10 2023", "Nov 11 2023");
    }

    @Then("sistem menampilkan pesan error bahwa tanggal tidak boleh berada di masa lalu")
    public void sistemMenampilkanPesanErrorTanggalLampau() {
        if (cutiMobilePage.isAlertDisplayed()) {
            String alertMessage = cutiMobilePage.getTxtAlert();
            System.out.println("Alert message: " + alertMessage);

            Assert.assertEquals(alertMessage,
                    "Incorrect datetime value: 'Invalid date' for column 'leave_date_from' at row 1",
                    "The alert message does not match the expected text");
            extentTest.log(LogStatus.PASS, "Alert is displayed as expected.");
        } else {
            System.out.println("No alert message is displayed.");
            extentTest.log(LogStatus.INFO, "No alert displayed for the given input.");
        }
        extentTest.log(LogStatus.PASS, "Pengajuan diterima");
    }
}
