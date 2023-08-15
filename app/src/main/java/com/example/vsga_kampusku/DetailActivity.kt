package com.example.vsga_kampusku

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.vsga_kampusku.data.AppDatabase
import com.example.vsga_kampusku.data.entity.Mahasiswa

class DetailActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        database = AppDatabase.getInstance(applicationContext)

        val textViewNomorMahasiswa = findViewById<TextView>(R.id.textViewNomorMahasiswa)
        val textViewNama = findViewById<TextView>(R.id.textViewNama)
        val textViewTanggalLahir = findViewById<TextView>(R.id.textViewTanggalLahir)
        val textViewJenisKelamin = findViewById<TextView>(R.id.textViewJenisKelamin)
        val textViewAlamat = findViewById<TextView>(R.id.textViewAlamat)

        val intent = intent
        if (intent != null && intent.hasExtra("id")) {
            val id = intent.getIntExtra("id", 0)
            if (id != -1) {
                val mahasiswa = database.mhsDao().get(id)
                textViewNomorMahasiswa.text = "Nomor Mahasiswa: ${mahasiswa.uid}"
                textViewNama.text = "Nama: ${mahasiswa.namaLengkap}"
                textViewTanggalLahir.text = "Tanggal Lahir: ${mahasiswa.tanggalLahir}"
                textViewJenisKelamin.text = "Jenis Kelamin: ${mahasiswa.jenisKelamin}"
                textViewAlamat.text = "Alamat: ${mahasiswa.alamat}"
            }
        }
    }
}
