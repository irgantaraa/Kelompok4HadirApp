Feature: Menampilkan Data Cuti Karyawan
  Sebagai pengguna, saya ingin menampilkan data cuti karyawan berdasarkan filter tertentu seperti nama, tanggal, dan departemen.

  # Scenario 1 TCC.Hadir.04.014
  Scenario: Menampilkan data cuti dengan input tanggal yang sesuai
    Given user berada di halaman data cuti
    When user memasukkan tanggal yang valid
    Then sistem menampilkan data cuti dalam rentang tanggal yang sesuai

  # Scenario 2 TCC.Hadir.04.015
  Scenario: Menampilkan data cuti dengan input nama dan tanggal yang sesuai
    Given user berada di halaman data cuti
    When user mengisi nama valid dan tanggal valid
    Then sistem menampilkan data cuti berdasarkan nama dan tanggal yang sesuai

  # Scenario 3 TCC.Hadir.04.016
  Scenario: Menampilkan data cuti input nama tidak valid
    Given user berada di halaman data cuti
    When user tidak input nama tidak valid
    Then sistem tidak menampilkan data nama tidak valid

#  Scenario 4 TCC.Hadir.04.017
  Scenario: Menampilkan data cuti input angka
    Given user berada di halaman data cuti
    When user melakukan input angka
    Then sistem tidak menampilkan data angka

#  Scenario: 5 TCC.Hadir.04.018
  Scenario: Menampilkan data cuti tanpa mengisi nama, tanggal, dan filter
    Given user berada di halaman data cuti
    When user tidak mengisi nama, tanggal, dan filter
    Then sistem menampilkan semua data cuti tanpa filter tanggal

  # Scenario 6 TCC.Hadir.04.019
  Scenario: Menampilkan data cuti dengan input tanggal tanpa memilih start date
    Given user berada di halaman data cuti
    When user tidak mengisi start date tetapi mengisi end date
    Then sistem menampilkan semua data cuti dengan mengisi start date
#
  # Scenario 7 TCC.Hadir.04.020
  Scenario: Menampilkan data cuti dengan input tanggal tanpa memilih end date
    Given user berada di halaman data cuti
    When user mengisi start date tetapi tidak mengisi end date
    Then sistem menampilkan semua data cuti dengan mengisi end date

# Scenario 8 TCC.Hadir.04.021
  Scenario: Menampilkan data cuti dengan input tanggal yang akan datang
    Given user berada di halaman data cuti
    When user mengisi tanggal yang akan datang
    Then sistem tidak menampilkan semua data cuti dengan tanggal yang akan datang

  # Scenario 9 TCC.Hadir.04.022
  Scenario: Menampilkan data cuti dengan filter departemen
    Given user berada di halaman data cuti
    When user memilih filter departemen
    Then sistem menampilkan data cuti hanya untuk departemen valid

# Scenario 10 TCC.Hadir.04.023
  Scenario: Menampilkan data cuti dengan filter yang tidak valid
    Given user berada di halaman data cuti
    When user memilih filter departemen yang tidak valid
    Then sistem tidak menampilkan data cuti hanya untuk departemen valid

  # Scenario 11 TCC.Hadir.04.024
  Scenario: Menampilkan data cuti dengan filter nama dan departemen
    Given user berada di halaman data cuti
    When user mengisi nama valid dan filter departemen valid
    Then sistem menampilkan data cuti hanya untuk nama valid di departemen valid

  # Scenario 12 TCC.Hadir.04.025
  Scenario: Menggunakan tombol Batal untuk keluar dari modal filter
    Given user berada di halaman data cuti
    And user membuka modal filter
    When user mengklik tombol "Batal"
    Then modal filter tertutup dan pengguna kembali ke halaman utama tanpa perubahan filter pada cuti

  # Scenario 13 TCC.Hadir.04.026
  Scenario: Menghapus filter yang dipilih dengan tombol X
    Given user berada di halaman data cuti
    And user mengisi nama dan filter departemen valid
    When user mengklik tombol X pada filter departemen
    Then filter departemen "Amex Merchant" dihapus dan data ditampilkan tanpa filter departemen pada cuti

#  Scenario 14: TCC.Hadir.04.027
  Scenario: Melakukan edit pada data cuti salah satu karyawan
    Given user berada di halaman data cuti
    When user mengklik tombol edit pada data cuti karyawan
    And modal edit cuti muncul dengan dua form upliner Atasan 2 dan Atasan 3 cuti
    And user melakukan edit data
    And user mengklik tombol ubah data
    Then data cuti karyawan berhasil diperbarui dengan diperbarui dengan upliner yang baru
