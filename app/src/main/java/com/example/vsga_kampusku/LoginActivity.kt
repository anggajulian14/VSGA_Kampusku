package com.example.vsga_kampusku

import android.content.SharedPreferences
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {

    private val Correctusername = "123"
    private val Correctpassword = "123"

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("login_info", MODE_PRIVATE)


        val UsernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val PasswrodEditText = findViewById<EditText>(R.id.passwordEditText)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val username = UsernameEditText.text.toString()
            val password = PasswrodEditText.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Login Gagal")
                alertDialogBuilder.setMessage("Username dan Password harus diisi")
                alertDialogBuilder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            } else if (username == Correctusername && password == Correctpassword) {
                val editor = sharedPreferences.edit()
                editor.putBoolean("is_logged_in", true)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Login Gagal")
                alertDialogBuilder.setMessage("Username atau Password salah, silahkan coba lagi")
                alertDialogBuilder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
        }


    }
}