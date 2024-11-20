package com.juaracoding;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = {
        "src/main/resources/features/07ClientUpliner.feature",
        "src/main/resources/features/06AbsenPoint.feature",
        "src/main/resources/features/01IzinPulangCepat.feature",
        "src/main/resources/features/02Cuti.feature",
        "src/main/resources/features/03Jabatan.feature",
        "src/main/resources/features/05SakitMobile.feature",
        "src/main/resources/features/04CutiMobile.feature"
        },
        glue = "com.juaracoding",
        plugin = {"pretty","html:target/cucumber-report.html","json:target/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
