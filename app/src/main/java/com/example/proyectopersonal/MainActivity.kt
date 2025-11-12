package com.example.proyectopersonal

import Util.Util
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnNext: Button= findViewById<Button>(R.id.btnNextLog_main)
        btnNext.setOnClickListener(View.OnClickListener {view -> Util.openActivity(this,
            LoginActivity::class.java)})

        val btnRegister: Button= findViewById<Button>(R.id.btnRegis_main)
        btnRegister.setOnClickListener { View.OnClickListener{view -> Util.openActivity(this,
            RegisterActivity::class.java) } }
    }

}