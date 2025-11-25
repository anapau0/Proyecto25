package com.example.proyectopersonal

import Util.Util
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import Data.MemoryDataManager

class PaymentActivity : AppCompatActivity() {

    private lateinit var txtItemCount: TextView
    private lateinit var txtTotalPayment: TextView
    private lateinit var radioCreditCard: RadioButton
    private lateinit var radioSinpe: RadioButton
    private lateinit var radioCash: RadioButton
    private lateinit var btnConfirmPayment: MaterialButton
    private lateinit var btnCancelPayment: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.payment)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtItemCount = findViewById(R.id.txtItemCount)
        txtTotalPayment = findViewById(R.id.txtTotalPayment)
        radioCreditCard = findViewById(R.id.radioCreditCard)
        radioSinpe = findViewById(R.id.radioSinpe)
        radioCash = findViewById(R.id.radioCash)
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment)
        btnCancelPayment = findViewById(R.id.btnCancelPayment)

        loadOrderSummary()

        btnConfirmPayment.setOnClickListener {
            processPayment()
        }

        btnCancelPayment.setOnClickListener {
            Util.openActivity(this, CartActivity::class.java)
            finish()
        }
    }

    private fun loadOrderSummary() {
        val cartItems = MemoryDataManager.getCartItems()
        val total = MemoryDataManager.getCartTotal()
        val itemCount = cartItems.sumOf { it.quantity }

        txtItemCount.text = itemCount.toString()
        txtTotalPayment.text = String.format("₡%.2f", total)

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            Util.openActivity(this, MenuActivity::class.java)
            finish()
        }
    }

    private fun processPayment() {
        val paymentMethod = when {
            radioCreditCard.isChecked -> "Tarjeta de Crédito/Débito"
            radioSinpe.isChecked -> "SINPE Móvil"
            radioCash.isChecked -> "Efectivo"
            else -> "Tarjeta de Crédito/Débito"
        }

        val total = MemoryDataManager.getCartTotal()
        val itemCount = MemoryDataManager.getCartItems().sumOf { it.quantity }

        showConfirmationDialog(paymentMethod, total, itemCount)
    }

    private fun showConfirmationDialog(paymentMethod: String, total: Double, itemCount: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("✅ ¡Compra Confirmada!")
        builder.setMessage(
            """
            Tu pedido ha sido procesado exitosamente.
            
            Método de pago: $paymentMethod
            Total de artículos: $itemCount
            Total pagado: ₡${String.format("%.2f", total)}
            
            ¡Gracias por tu compra!
            Tu pedido llegará pronto.
            """.trimIndent()
        )

        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
            MemoryDataManager.clearCart()
            Util.openActivity(this, MenuActivity::class.java)
            finish()
        }

        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()
    }


}