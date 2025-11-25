package com.example.proyectopersonal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import Data.MemoryDataManager

class CartAdapter(
    private val onQuantityChanged: () -> Unit,
    private val onDeleteItem: (String) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var items = listOf<MemoryDataManager.CartItem>()

    inner class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val txtQuantity: TextView = view.findViewById(R.id.txtQuantity)
        val txtSubtotal: TextView = view.findViewById(R.id.txtSubtotal)
        val btnPlus: MaterialButton = view.findViewById(R.id.btnPlus)
        val btnMinus: MaterialButton = view.findViewById(R.id.btnMinus)
        val btnDelete: MaterialButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.txtName.text = item.name
        holder.txtPrice.text = String.format("₡%.2f", item.price)
        holder.txtQuantity.text = item.quantity.toString()
        holder.txtSubtotal.text = String.format("₡%.2f", item.getTotal())

        holder.btnPlus.setOnClickListener {
            MemoryDataManager.increaseQuantity(item.name)
            onQuantityChanged()
        }

        holder.btnMinus.setOnClickListener {
            MemoryDataManager.decreaseQuantity(item.name)
            onQuantityChanged()
        }

        holder.btnDelete.setOnClickListener {
            onDeleteItem(item.name)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<MemoryDataManager.CartItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}