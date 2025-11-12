package com.example.proyectopersonal

import Controller.RegisterController
import Entity.Register
import Util.Util
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class RegisterActivity: AppCompatActivity(){

    private lateinit var txtName: EditText
    private lateinit var txtBirthdate: EditText
    private lateinit var txtMail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtAddress: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        txtName=findViewById<EditText>(R.id.txtName_register)
        txtBirthdate=findViewById<EditText>(R.id.txtBirthdate_register)
        txtMail=findViewById<EditText>(R.id.txtMail_register)
        txtPassword=findViewById<EditText>(R.id.txtPassword_register)
        txtPhone=findViewById<EditText>(R.id.txtPhone_register)
        txtAddress=findViewById<EditText>(R.id.txtAddress_register)

        val btnBackMain: Button= findViewById<Button>(R.id.btnBack_register)
        btnBackMain.setOnClickListener(View.OnClickListener {view -> Util.openActivity(this,
            MainActivity::class.java)})
       // val btn_save= findViewById<Button>(R.id.btnSave_register)
      //  btn_save.setOnClickListener(View.OnClickListener{ view -> SaveRegister() })




    }


}

