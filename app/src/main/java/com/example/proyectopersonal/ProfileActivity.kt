package com.example.proyectopersonal

import Entity.Register
import Util.Util
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import Data.MemoryDataManager

class ProfileActivity : AppCompatActivity() {

    private var usuarioActual: Register? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuarioActual = MemoryDataManager.getAllUsers().lastOrNull<Register>()

        val txtName = findViewById<TextInputEditText>(R.id.txtName_profile)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail_profile)
        val txtPhone = findViewById<TextInputEditText>(R.id.txtPhone_profile)

        if (usuarioActual != null) {
            txtName.setText(usuarioActual!!.name)
            txtEmail.setText(usuarioActual!!.email)
            txtPhone.setText(usuarioActual!!.phone.ifEmpty { "No registrado" })
        } else {
            txtName.setText("Usuario no encontrado")
            txtEmail.setText("—")
            txtPhone.setText("—")
        }

        findViewById<Button>(R.id.btnEditProfile_Profile).setOnClickListener {
            Util.openActivity(this, EditProfileActivity::class.java)
        }

        findViewById<Button>(R.id.btnLogout_Profile).setOnClickListener {

            Util.openActivity(this, LoginActivity::class.java)
            finishAffinity() // Cierra todas las actividades anteriores
        }

        findViewById<Button>(R.id.btnHome_Profile).setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
        }

        findViewById<Button>(R.id.btnMenu_Profile).setOnClickListener {
            Util.openActivity(this, MenuActivity::class.java)
        }

        findViewById<Button>(R.id.btnCart_Profile).setOnClickListener {
            Util.openActivity(this, CartActivity::class.java)
        }
    }
}