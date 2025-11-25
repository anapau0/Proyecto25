package Entity

data class Register(
    var id: Int = 0,
    var name: String = "",
    var birthdate: String = "",
    var email: String = "",
    var password: String = "",
    var phone: String = "",
    var address: String = ""
) {
    // Opcional: nombre completo (lo puedes usar después)
    val fullName: String get() = name
}