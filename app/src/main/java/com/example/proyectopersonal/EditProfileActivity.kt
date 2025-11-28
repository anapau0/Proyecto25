package com.example.proyectopersonal

import Entity.Register
import Util.Util
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import Data.MemoryDataManager

class EditProfileActivity : AppCompatActivity() {

    private var currentUser: Register? = null
    private lateinit var imgProfile: ImageView
    private var photoBitmap: Bitmap? = null

    private val cameraPreviewLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        if (bitmap != null) {
            photoBitmap = bitmap
            imgProfile.setImageBitmap(bitmap)
        } else {
            Toast.makeText(this, "No se capturó ninguna imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private val selectImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { imageUri ->
                try {
                    photoBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    imgProfile.setImageURI(imageUri)
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al cargar la imagen", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editprofile)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editProfile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        currentUser = MemoryDataManager.getAllUsers().lastOrNull()

        imgProfile = findViewById(R.id.imgProfile_edit)
        val txtName = findViewById<TextInputEditText>(R.id.txtName_edit)
        val txtEmail = findViewById<TextInputEditText>(R.id.txtEmail_edit)
        val txtPhone = findViewById<TextInputEditText>(R.id.txtPhone_edit)
        val btnEditPhoto = findViewById<FloatingActionButton>(R.id.btnEditPhoto)

        if (currentUser == null) {
            Toast.makeText(this, "No hay usuario registrado", Toast.LENGTH_SHORT).show()
            Util.openActivity(this, LoginActivity::class.java)
            finish()
            return
        }

        txtName.setText(currentUser!!.name)
        txtEmail.setText(currentUser!!.email)
        txtPhone.setText(currentUser!!.phone)

        if (currentUser!!.photo != null) {
            imgProfile.setImageBitmap(currentUser!!.photo)
            photoBitmap = currentUser!!.photo
        }

        txtEmail.isEnabled = false

        btnEditPhoto.setOnClickListener {
            showPhotoOptions()
        }

        findViewById<Button>(R.id.btnSave_edit).setOnClickListener {
            saveChanges(txtName, txtPhone)
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

    private fun showPhotoOptions() {
        val options = arrayOf("Tomar foto", "Seleccionar de galería", "Cancelar")

        AlertDialog.Builder(this)
            .setTitle("Cambiar foto de perfil")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> takePhoto()
                    1 -> selectPhoto()
                    2 -> dialog.dismiss()
                }
            }
            .show()
    }

    private fun takePhoto() {
        cameraPreviewLauncher.launch(null)
    }

    private fun selectPhoto() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        selectImageLauncher.launch(intent)
    }

    private fun saveChanges(txtName: TextInputEditText, txtPhone: TextInputEditText) {
        val newName = txtName.text.toString().trim()
        val newPhone = txtPhone.text.toString().trim()

        if (newName.isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            txtName.requestFocus()
            return
        }

        if (newName.length < 3) {
            Toast.makeText(this, "El nombre debe tener al menos 3 caracteres", Toast.LENGTH_SHORT).show()
            txtName.requestFocus()
            return
        }

        if (newPhone.isEmpty()) {
            Toast.makeText(this, "El teléfono no puede estar vacío", Toast.LENGTH_SHORT).show()
            txtPhone.requestFocus()
            return
        }

        if (newPhone.length < 8) {
            Toast.makeText(this, "Ingresa un teléfono válido", Toast.LENGTH_SHORT).show()
            txtPhone.requestFocus()
            return
        }

        val updatedUser = currentUser!!.copy(
            name = newName,
            phone = newPhone,
            photo = photoBitmap
        )
        MemoryDataManager.saveUser(updatedUser)

        Toast.makeText(this, "✅ Perfil actualizado exitosamente", Toast.LENGTH_LONG).show()

        Util.openActivity(this, ProfileActivity::class.java)
        finish()
    }


}