package com.example.proyectopersonal

import Entity.Register
import Util.Util
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import Data.MemoryDataManager
import de.hdodenhof.circleimageview.CircleImageView
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    private var currentUser: Register? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.profile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        currentUser = MemoryDataManager.getAllUsers().lastOrNull()
        if (currentUser == null) {
            Toast.makeText(this, "No hay usuario registrado", Toast.LENGTH_SHORT).show()
            Util.openActivity(this, LoginActivity::class.java)
            finish()
            return
        }

        findViewById<TextInputEditText>(R.id.txtName_profile).isEnabled = false
        findViewById<TextInputEditText>(R.id.txtEmail_profile).isEnabled = false
        findViewById<TextInputEditText>(R.id.txtPhone_profile).isEnabled = false

        findViewById<Button>(R.id.btnEditProfile_Profile).setOnClickListener {
            Util.openActivity(this, EditProfileActivity::class.java)
            finish()
        }

        findViewById<Button>(R.id.btnLogout_Profile).setOnClickListener {
            showLogoutDialog()
        }

        findViewById<Button>(R.id.btnHome_Profile).setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
            finish()
        }
        findViewById<Button>(R.id.btnMenu_Profile).setOnClickListener {
            Util.openActivity(this, MenuActivity::class.java)
            finish()
        }
        findViewById<Button>(R.id.btnCart_Profile).setOnClickListener {
            Util.openActivity(this, CartActivity::class.java)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        currentUser = MemoryDataManager.getAllUsers().lastOrNull() ?: return

        findViewById<TextInputEditText>(R.id.txtName_profile).setText(currentUser!!.name)
        findViewById<TextInputEditText>(R.id.txtEmail_profile).setText(currentUser!!.email)
        findViewById<TextInputEditText>(R.id.txtPhone_profile).setText(
            currentUser!!.phone.ifEmpty { "No registrado" }
        )

        val imgProfile = findViewById<CircleImageView>(R.id.imgProfile_profile)
        val txtInitials = findViewById<TextView>(R.id.txtInitials)

        if (currentUser!!.photo != null) {
            imgProfile.setImageBitmap(currentUser!!.photo)
            imgProfile.visibility = android.view.View.VISIBLE
            txtInitials.visibility = android.view.View.GONE
        } else {
            imgProfile.visibility = android.view.View.GONE
            txtInitials.visibility = android.view.View.VISIBLE
            txtInitials.text = getInitials(currentUser!!.name)
        }
    }

    private fun getInitials(name: String): String {
        val words = name.trim().split(" ")
        return if (words.size >= 2) {
            "${words[0].first()}${words[1].first()}".uppercase()
        } else {
            words[0].take(2).uppercase()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Cerrar Sesión")
            .setMessage("¿Estás seguro que deseas cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->
                Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                Util.openActivity(this, MainActivity::class.java)
                finishAffinity()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }


}