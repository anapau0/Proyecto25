package com.example.proyectopersonal

import Util.Util
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import Data.MemoryDataManager

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var txtTotal: TextView
    private lateinit var adapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cart)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cart)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerCartItems)
        txtTotal = findViewById(R.id.txtTotalCart)

        // Configurar RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CartAdapter(
            onQuantityChanged = { updateCart() },
            onDeleteItem = { itemName ->
                MemoryDataManager.removeFromCart(itemName)
                updateCart()
            }
        )
        recyclerView.adapter = adapter

        // Botones de navegación
        findViewById<Button>(R.id.btnHome_Cart).setOnClickListener {
            Util.openActivity(this, MainActivity::class.java)
        }
        findViewById<Button>(R.id.btnMenu_Cart).setOnClickListener {
            Util.openActivity(this, MenuActivity::class.java)
        }
        findViewById<Button>(R.id.btnProfile_Cart).setOnClickListener {
            Util.openActivity(this, ProfileActivity::class.java)
        }
        findViewById<Button>(R.id.btnBackto_Cart).setOnClickListener {
            Util.openActivity(this, MenuActivity::class.java)
        }
        findViewById<Button>(R.id.btn_PayCart).setOnClickListener {
            if (MemoryDataManager.getCartItems().isEmpty()) {
                android.widget.Toast.makeText(this, "El carrito está vacío", android.widget.Toast.LENGTH_SHORT).show()
            } else {
                Util.openActivity(this, PaymentActivity::class.java)
            }
        }

        updateCart()
    }

    private fun updateCart() {
        val items = MemoryDataManager.getCartItems()
        adapter.updateItems(items)

        val total = MemoryDataManager.getCartTotal()
        txtTotal.text = String.format("₡%.2f", total)
    }

    override fun onResume() {
        super.onResume()
        updateCart()
    }
}