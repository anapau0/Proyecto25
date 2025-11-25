package Data

import Entity.Register

object MemoryDataManager {

    private val users = mutableListOf<Register>()
    private val cartItems = mutableListOf<CartItem>()

    // ========== GESTIÓN DE USUARIOS ==========

    fun getAllUsers(): List<Register> {
        return users.toList()
    }

    fun saveUser(user: Register) {
        if (user.id == 0) {
            val nuevoId = if (users.isEmpty()) 1 else users.maxOf { it.id } + 1
            users.add(user.copy(id = nuevoId))
        } else {
            val index = users.indexOfFirst { it.id == user.id }
            if (index != -1) users[index] = user else users.add(user)
        }
    }

    fun getUserByEmail(email: String): Register? {
        return users.find { it.email.equals(email, ignoreCase = true) }
    }

    fun addUser(user: Register) = users.add(user)

    fun removeUser(id: Int) = users.removeAll { it.id == id }

    // ========== GESTIÓN DEL CARRITO ==========

    data class CartItem(
        val name: String,
        val price: Double,
        var quantity: Int = 1
    ) {
        fun getTotal(): Double = price * quantity
    }

    fun addToCart(name: String, price: Double) {
        val existing = cartItems.find { it.name == name }
        if (existing != null) {
            existing.quantity += 1
        } else {
            cartItems.add(CartItem(name, price))
        }
    }

    fun getCartItems(): List<CartItem> = cartItems.toList()

    fun increaseQuantity(name: String) {
        val item = cartItems.find { it.name == name }
        item?.let { it.quantity += 1 }
    }

    fun decreaseQuantity(name: String) {
        val item = cartItems.find { it.name == name }
        if (item != null && item.quantity > 1) {
            item.quantity -= 1
        } else if (item != null && item.quantity == 1) {
            // Si la cantidad es 1, eliminar el item
            cartItems.removeAll { it.name == name }
        }
    }

    fun removeFromCart(name: String) {
        cartItems.removeAll { it.name == name }
    }

    fun getCartTotal(): Double = cartItems.sumOf { it.getTotal() }

    fun clearCart() = cartItems.clear()
}