package Entity

import android.graphics.Bitmap

data class Register(
    val id: Int = 0,
    val name: String,
    val email: String,
    var password: String,
    val phone: String,
    val address: String,
    val birthdate: String,
    val photo: Bitmap? = null
)