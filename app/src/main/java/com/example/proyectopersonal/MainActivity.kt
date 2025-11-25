package com.example.proyectopersonal

import Util.Util
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        window.setDecorFitsSystemWindows(false)
        setContentView(R.layout.activity_main)
        findViewById<android.view.View>(R.id.payment).isClickable = false


        val btnNext = findViewById<Button>(R.id.btnNextLog_main)
        btnNext.setOnClickListener {
            Util.openActivity(this, LoginActivity::class.java)
        }

        val btnRegister = findViewById<Button>(R.id.btnRegis_main)
        btnRegister.setOnClickListener {
            Util.openActivity(this, RegisterActivity::class.java)
        }
        btnNext.isFocusable = false
        btnNext.isFocusableInTouchMode = false

        btnRegister.isFocusable = false
        btnRegister.isFocusableInTouchMode = false
    }
}