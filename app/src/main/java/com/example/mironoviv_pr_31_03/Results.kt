package com.example.mironoviv_pr_31_03

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class Results : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private val MY_SETTINGS = "my_settings"
    lateinit var metres : TextView
    lateinit var result : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        pref = getSharedPreferences(MY_SETTINGS, MODE_PRIVATE)

        metres = findViewById<TextView>(R.id.resmetres)
        result = findViewById<TextView>(R.id.res)
        var realresult = pref.getString("Result", "").toString().toDouble()

        metres.text = metres.text.toString() + pref.getString("ResultMetres", "")
        result.text = result.text.toString() + "${"%.2f".format(realresult)}" + " тыс. рублей"
    }

    fun GoAut(view: View) {
        val intent = Intent(this, Authorisation::class.java)
        startActivity(intent)
    }
}