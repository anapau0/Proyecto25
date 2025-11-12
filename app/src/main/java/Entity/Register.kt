package Entity

class Register {
    private var name: String = ""
    private var birthdate: String = ""
    private var email: String = ""
    private var password: String = ""
    private var phone: String = ""
    private var address: String = ""

    constructor(
        name: String, birthdate: String, email: String,
        password: String, phone: String, address: String
    ) {
        this.name = name
        this.birthdate = birthdate
        this.email = email
        this.password = password
        this.phone = phone
        this.address = address

    }

    var Name: String
        get() = name
        set(value) {
            name = value
        }

    var Birthdate: String
        get() = birthdate
        set(value) {
            birthdate = value
        }

    var Email: String
        get() = email
        set(value) {
            email = value
        }

    var Password: String
        get() = password
        set(value) {
            password = value
        }

    var Phone: String
        get() = phone
        set(value) {
            phone = value
        }

    var Address: String
        get() = address
        set(value) {
            address = value
        }



}