package Entity

import java.sql.Date

class Order {
    private var orderId: Int = 0
    private lateinit var user: User
    private lateinit var product: Product
    private var quantity: Int = 0
    private lateinit var orderDate: Date
    private var totalPrice: Double = 0.0

    constructor(orderId: Int, user: User, product: Product,
                quantity: Int, orderDate: Date, totalPrice: Double)

    {
        this.orderId = orderId
        this.user = user
        this.product = product
        this.quantity = quantity
        this.orderDate = orderDate
        this.totalPrice = totalPrice



    }
    var OrderId: Int
        get() = orderId
        set(value) { orderId = value }

    var User: User
        get() = user
        set(value) { user = value }

    var Product: Product
        get() = product
        set(value) { product = value }

    var Quantity: Int
        get() = quantity
        set(value) { quantity = value }

    var OrderDate: Date
        get() = orderDate
        set(value) { orderDate = value }

    var TotalPrice: Double
        get() = totalPrice
        set(value) { totalPrice = value }

    fun CalculateTotalPrice() {
        totalPrice = product.Price * quantity
    }



}