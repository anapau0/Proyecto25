package com.example.proyectopersonal

import Entity.Register
import Util.Util
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import Data.MemoryDataManager
import android.util.Log

class RegisterActivity : AppCompatActivity() {

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

        // Tus findViewById
        txtName = findViewById(R.id.txtName_register)
        txtBirthdate = findViewById(R.id.txtBirthdate_register)
        txtMail = findViewById(R.id.txtMail_register)
        txtPassword = findViewById(R.id.txtPassword_register)
        txtPhone = findViewById(R.id.txtPhone_register)
        txtAddress = findViewById(R.id.txtAddress_register)

        val btnBackMain: Button = findViewById(R.id.btnBack_register)
        btnBackMain.setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
            finish()
        }

        val btnSave = findViewById<Button>(R.id.btnSave_register)
        btnSave.setOnClickListener {
            val name = txtName.text.toString().trim()
            val birthdate = txtBirthdate.text.toString().trim()
            val email = txtMail.text.toString().trim().lowercase()
            val password = txtPassword.text.toString()
            val phone = txtPhone.text.toString().trim()
            val address = txtAddress.text.toString().trim()

            // Validaciones
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || birthdate.isEmpty() || phone.isEmpty() || address.isEmpty()
                || address.isEmpty()) {
                Toast.makeText(this, R.string.txtAlert_Register, Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Correo no válido", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "Contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (MemoryDataManager.getUserByEmail(email) != null) {
                Toast.makeText(this, "Este correo ya está registrado", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val nuevoId = (MemoryDataManager.getAllUsers().maxOfOrNull { it.id } ?: 0) + 1

            val nuevoUsuario = Register(
                id = nuevoId,
                name = name,
                birthdate = birthdate,
                email = email,
                password = password,
                phone = phone,
                address = address
            )

            MemoryDataManager.saveUser(nuevoUsuario)

            Toast.makeText(this, "¡Registro exitoso! Bienvenido $name", Toast.LENGTH_LONG).show()
            Util.openActivity(this, LoginActivity::class.java)
            finish()
        }
    }
}