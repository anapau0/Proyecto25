package Entity

class User {
    private var id: String=""
    private var name: String=""
    private var lastName: String=""
    private var email: String=""
    private var password: String=""
    private var phone: Int=0

constructor(id: String, name: String, lastName: String,
            email: String, password: String, phone: Int){
        this.id = id
        this.name = name
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    var Id: String
        get() = id
        set(value) { id = value }

    var Name: String
        get() = name
        set(value) { name = value }

    var LastName: String
        get() = lastName
        set(value) { lastName = value }

    var Email: String
        get() = email
        set(value) { email = value }

    var Password: String
        get() = password
        set(value) { password = value }

    var Phone: Int
        get() = phone
        set(value) { phone = value }

    fun Fullname() = "${this.name } ${this.lastName}"

}
