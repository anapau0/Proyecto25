package com.example.proyectopersonal

import Entity.Register
import Util.Util
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import Data.MemoryDataManager
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var txtEmailLogin: TextInputEditText
    private lateinit var txtPasswordLogin: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtEmailLogin = findViewById(R.id.txtEmail_login)
        txtPasswordLogin = findViewById(R.id.editTextTextPassword3)

        val btnLogin = findViewById<Button>(R.id.btnAcept_login)
        val btnRecover = findViewById<Button>(R.id.btnforgotpass_login)
        val btnHome = findViewById<Button>(R.id.btnHome_Login)

        btnHome.setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
            finish()
        }

        btnRecover.setOnClickListener {
            Util.openActivity(this, RecoverActivity::class.java)
        }

        btnLogin.setOnClickListener {
            val email = txtEmailLogin.text.toString().trim().lowercase()
            val password = txtPasswordLogin.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu correo", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa tu contraseña", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }


            val usuario = MemoryDataManager.getUserByEmail(email)

            if (usuario == null) {
                Toast.makeText(this, "Este correo no está registrado", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (usuario.password != password) {
                Toast.makeText(this, R.string.txtAlert_Login, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "¡Bienvenido de vuelta, ${usuario.name}!", Toast.LENGTH_LONG).show()
            Util.openActivity(this, MenuActivity::class.java)
            finish()
        }
    }
}