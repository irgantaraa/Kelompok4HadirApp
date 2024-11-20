Feature: Menampilkan Info Cuti

# Scenario: 1 TCC.Hadir.04.108
  Scenario: Menampilkan informasi cuti
    Given pengguna telah login ke aplikasi
    When pengguna membuka halaman pengajuan cuti
    Then informasi cuti harus ditampilkan dengan benar

# Scenario: 2 - Mengajukan cuti dengan data valid TCC.Hadir.04.109
  Scenario: Mengajukan cuti dengan data valid
    Given pengguna telah login ke aplikasi
    When pengguna membuka halaman pengajuan cuti
    And pengguna memilih tipe cuti dan memilih tanggal mulai dan tanggal selesai
    And pengguna mengisi catatan
    When pengguna mengklik tombol "Ajukan"
    Then cuti berhasil diajukan dan sistem menampilkan konfirmasi pengajuan cuti

# Scenario: 3 - Mengajukan cuti dengan data kosong atau tidak valid TCC.Hadir.04.110
  Scenario: Mengajukan cuti dengan data kosong atau tidak valid
    Given pengguna telah login ke aplikasi
    When pengguna membuka halaman pengajuan cuti
    And pengguna tidak mengisi tanggal mulai dan tanggal selesai
    And pengguna tidak mengisi catatan
    When pengguna mengklik tombol "Ajukan"
    Then sistem menampilkan pesan error bahwa semua field harus diisi

# Scenario: 4 - Ajukan cuti dengan memilih tipe cuti dan field lain kosong TCC.Hadir.04.111
  Scenario: Ajukan cuti dengan memilih tipe cuti dan field lain kosong
    Given pengguna telah login ke aplikasi
    When pengguna membuka halaman pengajuan cuti
    And pengguna tidak mengisi tanggal mulai, tanggal selesai, dan catatan
    When pengguna klik tombol Ajukan
    Then sistem menampilkan pesan error bahwa tanggal mulai, tanggal selesai, dan catatan harus diisi

# Scenario: 5 - Ajukan cuti dengan memilih tanggal cuti dan field lain kosong TCC.Hadir.04.112
  Scenario: Ajukan cuti dengan memilih tanggal cuti dan field lain kosong
    Given pengguna telah login ke aplikasi
    When pengguna membuka halaman pengajuan cuti
    And pengguna memilih tanggal mulai dan tanggal selesai
    And pengguna tidak mengisi catatan
    When pengguna mengklik tombol "Ajukan"
    Then sistem menampilkan pesan error

# Scenario: 6 - Ajukan cuti dengan mengisi form catatan dan field lain kosong TCC.Hadir.04.113
  Scenario: Ajukan cuti dengan mengisi form catatan dan field lain kosong
    Given pengguna telah login ke aplikasi
    When pengguna membuka halaman pengajuan cuti
    And pengguna tidak mengisi tanggal mulai dan tanggal selesai
    And pengguna mengisi catatan
    When pengguna mengklik tombol "Ajukan"
    Then sistem menampilkan pesan error bahwa tanggal mulai dan tanggal selesai harus diisi

# Scenario: 7 - Ajukan cuti dengan tanggal yang sudah berlalu TCC.Hadir.04.114
  Scenario: Ajukan cuti dengan tanggal yang sudah berlalu
    Given pengguna telah login ke aplikasi
    When pengguna membuka halaman pengajuan cuti
    And pengguna memilih tanggal mulai "2023-11-10" dan tanggal selesai "2023-11-11"
    When pengguna mengklik tombol "Ajukan"
    Then sistem menampilkan pesan error bahwa tanggal tidak boleh berada di masa lalu
