package com.example.proyectopersonal

import Util.Util
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import Data.MemoryDataManager

class RecoverActivity : AppCompatActivity() {

    private lateinit var txtEmail: TextInputEditText
    private lateinit var txtNewPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password_recover)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recoverpssw)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtEmail = findViewById(R.id.txtEmail_recover)
        txtNewPassword = findViewById(R.id.txtPassword_recover)

        val btnRecover = findViewById<Button>(R.id.btnRecover_recover)
        btnRecover.setOnClickListener {
            recoverPassword()
        }

        try {
            val btnBackToLogin = findViewById<Button>(R.id.btnBackToLogin)
            btnBackToLogin?.setOnClickListener {
                Util.openActivity(this, LoginActivity::class.java)
                finish()
            }
        } catch (e: Exception) {
        }

        val btnMain = findViewById<Button>(R.id.btnHome_Recover)
        btnMain.setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
            finish()
        }
    }

    private fun recoverPassword() {
        val email = txtEmail.text.toString().trim()
        val newPassword = txtNewPassword.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
            txtEmail.requestFocus()
            return
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Ingresa un correo válido", Toast.LENGTH_SHORT).show()
            txtEmail.requestFocus()
            return
        }

        if (newPassword.isEmpty()) {
            Toast.makeText(this, "Ingresa una nueva contraseña", Toast.LENGTH_SHORT).show()
            txtNewPassword.requestFocus()
            return
        }

        if (newPassword.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            txtNewPassword.requestFocus()
            return
        }

        val user = MemoryDataManager.getUserByEmail(email)

        if (user == null) {
            Toast.makeText(
                this,
                "No existe una cuenta con ese correo electrónico",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val updatedUser = user.copy(password = newPassword)
        MemoryDataManager.saveUser(updatedUser)

        Toast.makeText(
            this,
            "✅ Contraseña actualizada exitosamente",
            Toast.LENGTH_LONG
        ).show()

        txtEmail.setText("")
        txtNewPassword.setText("")

        txtEmail.postDelayed({
            Util.openActivity(this, LoginActivity::class.java)
            finish()
        }, 1500)
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}