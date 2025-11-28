package com.example.proyectopersonal

import Entity.Register
import Util.Util
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputEditText
import Data.MemoryDataManager
import androidx.cardview.widget.CardView

class MenuActivity : AppCompatActivity() {

    private var CurrentUser: Register? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menu)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CurrentUser = MemoryDataManager.getAllUsers().lastOrNull()

        val txtTitulo = findViewById<TextView>(R.id.textView33)
        if (CurrentUser != null) {
            txtTitulo.text = "¡Hola, ${CurrentUser!!.name.split(" ").first()}!"
        } else {
            txtTitulo.text = "Menú del Día"
        }

        val searchView = findViewById<TextInputEditText>(R.id.searchView_Menu)
        val cards = listOf(
            findViewById<CardView>(R.id.card1),
            findViewById<CardView>(R.id.card2),
            findViewById<CardView>(R.id.card3),
            findViewById<CardView>(R.id.card4)
        )

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().lowercase()
                if (query.isEmpty()) {
                    cards.forEach { it.isVisible = true }
                    return
                }

                val platos = listOf(
                    "arroz con camarones",
                    "filete de res y hongos",
                    "pasta al pomodoro",
                    "costillitas y risotto"
                )

                cards.forEachIndexed { index, card ->
                    card.isVisible = platos[index].lowercase().contains(query)
                }
            }
        })


        val products = listOf(
            Triple("Arroz con Camarones", 8500.0, R.id.select_op1),
            Triple("Filete de Res y Hongos", 12000.0, R.id.select_opc2),
            Triple("Pasta al Pomodoro", 7500.0, R.id.select_opc3),
            Triple("Costillitas y Risotto", 11500.0, R.id.select_opc4)
        )

        products.forEach { (nombre, precio, buttonId) ->
            findViewById<Button>(buttonId).setOnClickListener {
                MemoryDataManager.addToCart(nombre, precio)
                Toast.makeText(
                    this,
                    "$nombre agregado al carrito",
                    Toast.LENGTH_SHORT
                ).show()


            }
        }

        findViewById<Button>(R.id.btnHome_Menu).setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
        }

        findViewById<Button>(R.id.btncart_Menu).setOnClickListener {
            Util.openActivity(this, CartActivity::class.java)
        }

        findViewById<Button>(R.id.btnProfile_Menu).setOnClickListener {
            Util.openActivity(this, ProfileActivity::class.java)
        }
    }
}