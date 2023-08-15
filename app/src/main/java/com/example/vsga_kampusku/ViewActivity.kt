package com.example.vsga_kampusku

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.vsga_kampusku.adapter.MhsAdapter
import com.example.vsga_kampusku.data.AppDatabase
import com.example.vsga_kampusku.data.entity.Mahasiswa
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private var list = mutableListOf<Mahasiswa>()
    private lateinit var adapter: MhsAdapter
    private lateinit var database: AppDatabase

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        recyclerView = findViewById(R.id.recyclerView)
        fab = findViewById(R.id.fab)

        database = AppDatabase.getInstance(applicationContext)
        adapter = MhsAdapter(list)
        adapter.setDialog(object : MhsAdapter.Dialog{

            override fun onClick(position: Int) {
                val dialogOptions = AlertDialog.Builder(this@ViewActivity)
                dialogOptions.setTitle(list[position].namaLengkap)
                dialogOptions.setItems(R.array.item_option) { dialog, which ->
                    when (which) {
                        0 -> {
                            // Tampilkan data tanpa opsi edit
                            val intent = Intent(this@ViewActivity, DetailActivity::class.java)
                            val id = list[position].uid.toIntOrNull() ?: 0
                            intent.putExtra("id", id)
                            startActivity(intent)

                        }
                        1 -> {
                            // Edit data
                            val intent = Intent(this@ViewActivity, UpdateActivity::class.java)
                            val id = list[position].uid.toIntOrNull() ?: 0
                            intent.putExtra("id", id)
                            startActivity(intent)
                        }
                        2 -> {
                            // Hapus data
                            database.mhsDao().delete(list[position])
                            getData()
                        }
                    }
                    dialog.dismiss()
                }
                val dialogView = dialogOptions.create()
                dialogView.show()
            }

        })

        recyclerView.adapter = adapter // Inisialisasi adapter

        recyclerView.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        fab.setOnClickListener {
            intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        getData() // Panggil getData() saat activity di-resume
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        list.clear()
        list.addAll(database.mhsDao().getAll())
        adapter.notifyDataSetChanged()
    }
}
