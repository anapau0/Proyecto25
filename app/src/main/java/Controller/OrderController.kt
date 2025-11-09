package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.Order

class OrderController {
    private var dataManager: IDataManager = MemoryDataManager

    fun addOrder(order: Order) {
        try {
            dataManager.addOrder(order)
        } catch (e: Exception) {
            throw Exception("Error al agregar la orden")
        }
    }

    fun removeOrder(orderId: Int) {
        try {
            dataManager.removeOrder(orderId.toString())
        } catch (e: Exception) {
            throw Exception("Error al eliminar la orden")
        }
    }

    fun updateOrder(order: Order) {
        try {
            dataManager.updateOrder(order)
        } catch (e: Exception) {
            throw Exception("Error al actualizar la orden")
        }
    }
}
