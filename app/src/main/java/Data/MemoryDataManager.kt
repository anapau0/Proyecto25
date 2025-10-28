package Data

import Entity.Order
import Entity.Product
import Entity.User

object MemoryDataManager : IDataManager {

    //Usuarios
    private val users = mutableListOf<User>()

    override fun addUser(user: User) {
        users.add(user)
    }

    override fun removeUser(id: String) {
        users.removeIf { it.Id.trim() == id.trim() }
    }

    override fun updateUser(user: User) {
        removeUser(user.Id)
        addUser(user)
    }

    //Ordenes
    private val orders = mutableListOf<Order>()

    override fun addOrder(order: Order) {
        orders.add(order)
    }

    override fun removeOrder(id: String) {
        orders.removeIf { it.OrderId.toString().trim() == id.trim() }
    }

    override fun updateOrder(order: Order) {
        removeOrder(order.OrderId.toString())
        addOrder(order)
    }

    private val products = mutableListOf<Product>()
    override fun addProduct(product: Product) {
        products.add(product)
    }
    override fun removeProduct(id: String) {
        products.removeIf { it.Id_product.toString().trim() == id.trim() }
    }
    override fun updateProduct(product: Product) {
        removeProduct(product.Id_product.toString())
        addProduct(product)

    }
}
