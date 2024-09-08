package com.example.mironoviv_pr_31_03

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly

class Counting : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private val MY_SETTINGS = "my_settings"
    lateinit var square : EditText
    lateinit var flat : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counting)
        square = findViewById<EditText>(R.id.msquare)
        flat = findViewById<Spinner>(R.id.appartment)
        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)
        }

    fun GoBack(view: View) {
        val intent = Intent(this, Authorisation::class.java)
        startActivity(intent)
    }

    fun Count(view: View) {
        try {
            if (square.text.isNotEmpty()) {
                if (square.text.toString().isDigitsOnly()) {
                    var position = flat.getSelectedItemPosition()
                    val metr = 101.359
                    var res = 0.0
                    when (position) {
                        0 -> res = metr * square.text.toString().toDouble() * 1.4
                        1 -> res = metr * square.text.toString().toDouble()
                        2 -> res = metr * square.text.toString().toDouble() * 0.8
                        3 -> res = metr * square.text.toString().toDouble() * 1.1
                    }
                    pref.edit().putString("Result", res.toString()).commit()
                    pref.edit().putString("ResultMetres", square.text.toString()).commit()

                    val intent = Intent(this, Results::class.java)
                    startActivity(intent)
                } else {
                    val alert = AlertDialog.Builder(this)
                        .setTitle("Ошибка")
                        .setMessage("Введите только числа")
                        .setPositiveButton("Ok", null)
                        .create()
                        .show()
                }
            } else {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Обнаружены незаполненные поля")
                    .setMessage("Введите числом площадь квартиры в квадратных метрах")
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
            }
        }
        catch (e:Exception) {
            val alert = AlertDialog.Builder(this)
                .setTitle("Ошибка")
                .setMessage("Ошибка")
                .setPositiveButton("Ok", null)
                .create()
                .show()
        }
    }
}
