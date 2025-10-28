package Data

import Entity.Order
import Entity.Product
import Entity.User

interface IDataManager {
    fun addUser(person: User)
    fun updateUser(person: User)
    fun removeUser(id: String)

    fun addOrder(order: Order)
    fun updateOrder(order: Order)
    fun removeOrder(id: String)

    fun addProduct(product: Product)
    fun updateProduct(product: Product)
    fun removeProduct(id: String)
}
