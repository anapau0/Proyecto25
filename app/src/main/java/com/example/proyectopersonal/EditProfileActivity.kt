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
import com.google.android.material.textfield.TextInputEditText
import Data.MemoryDataManager

class EditProfileActivity : AppCompatActivity() {

    private var usuarioActual: Register? = null
git
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editprofile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editProfile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuarioActual = MemoryDataManager.getAllUsers().lastOrNull()

        val txtName = findViewById<TextInputEditText>(R.id.txtName_edit)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail_edit)
        val txtPhone = findViewById<TextInputEditText>(R.id.txtPhone_edit)

        if (usuarioActual == null) {
            Toast.makeText(this, "No hay usuario registrado", Toast.LENGTH_SHORT).show()
            Util.openActivity(this, LoginActivity::class.java)
            finish()
            return
        }

        txtName.setText(usuarioActual!!.name)
        txtEmail.setText(usuarioActual!!.email)
        txtPhone.setText(usuarioActual!!.phone)

        txtEmail.isEnabled = false

        findViewById<Button>(R.id.btnSave_edit).setOnClickListener {
            guardarCambios(txtName, txtPhone)
        }

        findViewById<Button>(R.id.btnCancel_edit).setOnClickListener {
            Util.openActivity(this, ProfileActivity::class.java)
            finish()
        }

        findViewById<Button>(R.id.btnHome_Edit).setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
            finish()
        }
        findViewById<Button>(R.id.btnMenu_Edit).setOnClickListener {
            Util.openActivity(this, MenuActivity::class.java)
            finish()
        }
        findViewById<Button>(R.id.btnCart_Edit).setOnClickListener {
            Util.openActivity(this, CartActivity::class.java)
            finish()
        }
        findViewById<Button>(R.id.btnProfile_edit).setOnClickListener {
            Util.openActivity(this, ProfileActivity::class.java)
            finish()
        }
    }

    private fun guardarCambios(txtName: TextInputEditText, txtPhone: TextInputEditText) {
        val nuevoNombre = txtName.text.toString().trim()
        val nuevoTelefono = txtPhone.text.toString().trim()

        if (nuevoNombre.isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            txtName.requestFocus()
            return
        }

        if (nuevoNombre.length < 3) {
            Toast.makeText(this, "El nombre debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show()
            txtName.requestFocus()
            return
        }

        if (nuevoTelefono.isEmpty()) {
            Toast.makeText(this, "El teléfono no puede estar vacío", Toast.LENGTH_SHORT).show()
            txtPhone.requestFocus()
            return
        }

        if (nuevoTelefono.length < 8) {
            Toast.makeText(this, "Ingresa un teléfono válido", Toast.LENGTH_SHORT).show()
            txtPhone.requestFocus()
            return
        }

        val usuarioActualizado = usuarioActual!!.copy(
            name = nuevoNombre,
            phone = nuevoTelefono
        )
        MemoryDataManager.saveUser(usuarioActualizado)

        Toast.makeText(this, "✅ Perfil actualizado exitosamente", Toast.LENGTH_LONG).show()

        Util.openActivity(this, ProfileActivity::class.java)
        finish()
    }


}