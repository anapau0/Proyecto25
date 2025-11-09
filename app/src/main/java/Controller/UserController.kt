package Controller

import Data.IDataManager
import Data.MemoryDataManager
import Entity.User

class UserController {

    private var dataManager: IDataManager = MemoryDataManager

    fun addUser(user: User) {
        try {
            dataManager.addUser(user)
        } catch (e: Exception) {
            throw Exception("Error al agregar el usuario")
        }
        fun updateUser(user: User) {
            try {
                dataManager.updateUser(user)
            }catch (e: Exception){
                throw Exception("Error al actualizar el usuario")
            }

        }
    }
}


