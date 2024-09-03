package com.example.mironoviv_pr_31_03

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class Authorization : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    lateinit var login : EditText
    lateinit var password : EditText
    private val MY_SETTINGS = "my_settings"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        login = findViewById<EditText>(R.id.login)
        password = findViewById<EditText>(R.id.password)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
    }
    fun GoTo(view: View) {
        if (login.text.isNotEmpty() and password.text.isNotEmpty())
        {
            if (pref.getBoolean("IsAuthorized", false) == false)
            {
                pref.edit().putBoolean("IsAuthorized", true).commit()
                pref.edit().putString("Login", login.text.toString()).commit()
                pref.edit().putString("Password", password.text.toString()).commit()

                val intent = Intent(this, Counting::class.java)
                startActivity(intent)
            }
            else
            {
                if ((pref.getString("Login", "") == login.text.toString()) and (pref.getString("Password", "") == password.text.toString()))
                {
                    val intent = Intent(this, Counting::class.java)
                    startActivity(intent)
                }
                else
                {
                    val alert = AlertDialog.Builder(this)
                        .setTitle("Ошибка")
                        .setMessage("Вы неверно ввели логин или пароль, попробуйте ещё раз")
                        .setPositiveButton("Ok", null)
                        .create()
                        .show()
                }
            }
        }
        else
        {
            val alert = AlertDialog.Builder(this)
                .setTitle("Обнаружены незаполненные поля")
                .setMessage("Введите логин и пароль")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }
    }
}