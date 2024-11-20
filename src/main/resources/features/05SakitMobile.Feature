Feature: Pengajuan Sakit dan Verifikasi Surat Sakit

#  Scenario: 1 TCC.Hadir.04.115
  Scenario: Pengguna mengajukan sakit dengan tanggal dan surat sakit valid
    Given Pengguna berada di halaman pengajuan sakit
    When Pengguna memilih tanggal dan meng-upload surat sakit valid
    Then Pengajuan diterima

#  Scenario: 2 TCC.Hadir.04.116
  Scenario: Verifikasi hanya menerima file gambar untuk surat sakit
    Given Pengguna berada di halaman pengajuan sakit
    When Pengguna meng-upload file bukan gambar
    Then Sistem menampilkan peringatan format file tidak valid

#  Scenario: 3 TCC.Hadir.04.117
  Scenario: Verifikasi peringatan jika surat sakit tidak di-upload atau tidak valid
    Given Pengguna berada di halaman pengajuan sakit
    When Pengguna memilih tanggal tanpa meng-upload surat sakit
    Then Sistem menampilkan peringatan

 #  Scenario: 4 TCC.Hadir.04.118
  Scenario: Verifikasi peringatan ajukan surat sakit tanpa input tanggal
    Given Pengguna berada di halaman pengajuan sakit
    When Pengguna memilih mengajukan sakit tanpa input tanggal
    Then Sistem menampilkan peringatan input tanggal

#  Scenario: 5 TCC.Hadir.04.119
  Scenario: Tombol "Batal" membatalkan tanggal yang dipilih
    Given Pengguna berada di halaman pengajuan sakit
    When Pengguna memilih tanggal
    And Pengguna menekan tombol "Batal"
    Then Start date dan end date kosong

#  Scenario: 6 TCC.Hadir.04.120
  Scenario: Tombol "Reset" menghapus tanggal dan file
    Given Pengguna berada di halaman pengajuan sakit
    When Pengguna memilih tanggal dan meng-upload surat sakit
    And Pengguna menekan tombol "Reset"
    Then Tanggal dan file dihapus