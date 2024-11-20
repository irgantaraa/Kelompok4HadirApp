Feature: Menampilkan Data Izin Pulang Cepat
  Sebagai pengguna, saya ingin menampilkan data izin pulang cepat berdasarkan filter tertentu seperti nama, tanggal, dan departemen.

# Scenario: 1 TCC.Hadir.04.001
  Scenario: Menampilkan data izin pulang cepat dengan input tanggal yang sesuai
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna memasukkan tanggal yang valid
    Then sistem menampilkan data izin pulang cepat dalam rentang tanggal yang sesuai

# Scenario: 2 TCC.Hadir.04.002
  Scenario: Menampilkan data izin pulang cepat dengan input nama dan tanggal yang sesuai
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna mengisi nama valid dan tanggal valid
    Then sistem menampilkan data izin pulang cepat berdasarkan nama dan tanggal yang sesuai

# Scenario: 3 TCC.Hadir.04.003
  Scenario: Menampilkan data izin pulang cepat dengan input nama tidak valid
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna mengisi nama tidak valid
    Then sistem menampilkan data izin pulang cepat berdasarkan input tidak valid

# Scenario: 4 TCC.Hadir.04.004
  Scenario: Menampilkan data izin pulang cepat dengan input angka
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna mengisi angka
    Then sistem tidak menampilkan data izin pulang cepat berdasarkan input angka

# Scenario: 5 TCC.Hadir.04.005
  Scenario: Menampilkan data izin pulang cepat tanpa mengisi nama, tanggal dan filter
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna tidak mengisi tanggal
    Then sistem menampilkan semua data izin pulang cepat tanpa filter tanggal

# Scenario: 6 TCC.Hadir.04.006
  Scenario: Menampilkan data izin pulang cepat dengan input tanggal tanpa memilih start date
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna tidak mengisi start date tetapi mengisi end date
    Then sistem menampilkan izin pulang cepat tanpa memilih start date


# Scenario: 7 TCC.Hadir.04.007
  Scenario: Menampilkan data izin pulang cepat dengan input tanggal tanpa memilih end date
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna mengisi start date tetapi tidak mengisi end date
    Then sistem menampilkan izin pulang cepat tanpa memilih end date

# Scenario: 8 TCC.Hadir.04.008
  Scenario: Menampilkan data izin pulang cepat dengan input tanggal yang akan datang
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna mengisi date yang akan datang
    Then sistem menampilkan izin pulang cepat yang akan datang

# Scenario: 9 TCC.Hadir.04.009
  Scenario: Menampilkan data izin pulang cepat dengan filter departemen
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna memilih filter departemen "IT Programmer"
    Then sistem menampilkan data izin pulang cepat hanya untuk departemen valid

#Scenario: 10 TCC.Hadir.04.010
  Scenario: Menampilkan data izin pulang cepat dengan filter tidak valid
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna memilih filter departemen "123"
    Then sistem menampilkan data izin pulang cepat dengan filter "123"

# Scenario: 11 TCC.Hadir.04.011
  Scenario: Menampilkan data izin pulang cepat dengan filter nama dan departemen
    Given pengguna berada di halaman data izin pulang cepat
    When pengguna mengisi filter nama valid dan filter departemen valid
    Then sistem menampilkan data izin pulang cepat hanya untuk nama valid di departemen valid

# Scenario: 12 TCC.Hadir.04.012
  Scenario: Menggunakan tombol Batal untuk keluar dari modal filter
    Given pengguna berada di halaman data izin pulang cepat
    And pengguna membuka modal filter
    When pengguna mengklik tombol "Batal"
    Then modal filter tertutup dan pengguna kembali ke halaman utama tanpa perubahan filter pada izin pulang cepat

# Scenario: 13 TCC.Hadir.04.013
  Scenario: Menghapus filter yang dipilih dengan tombol X
    Given pengguna berada di halaman data izin pulang cepat
    And pengguna mengisi filter nama dan filter departemen yang valid
    When pengguna mengklik tombol X pada filter nama "Amex Merchant"
    Then filter nama "Amex Merchant" dihapus dan data ditampilkan tanpa filter nama pada izin pulang cepat