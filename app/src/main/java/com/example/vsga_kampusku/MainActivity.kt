package com.example.vsga_kampusku

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE)

        if (!sharedPreferences.getBoolean("is_logged_in", false)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        val buttonInput = findViewById<ImageButton>(R.id.buttonInput)
        val buttonView = findViewById<ImageButton>(R.id.buttonView)
        val buttonDetail = findViewById<ImageButton>(R.id.buttonDetail)

        buttonInput.setOnClickListener{
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
        buttonView.setOnClickListener{
            val intent = Intent(this, ViewActivity::class.java)
            startActivity(intent)
        }
        buttonDetail.setOnClickListener{
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)
        }
    }
}
