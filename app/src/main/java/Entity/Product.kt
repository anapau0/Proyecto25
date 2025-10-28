package Entity

import android.graphics.Bitmap

class Product {

    private var id_product: Int= 0
    private var name: String= ""
    private var description: String= ""
    private var price: Double= 0.0
    private var stock: Int= 0
    private var latitude: Int=0
    private var longitude:Int=0
    private  var photo: Bitmap?

    constructor(id_product: Int, name: String, description: String,
                price: Double, stock: Int, latitude: Int,
                longitude: Int, photo: Bitmap?){
        this.id_product = id_product
        this.name = name
        this.description = description
        this.price = price
        this.stock = stock
        this.latitude = latitude
        this.longitude = longitude
        this.photo = photo

    }
    var Id_product: Int
        get() = id_product
        set(value) { id_product = value }

    var Name: String
        get() = name
        set(value) { name = value }

    var Description: String
        get() = description
        set(value) { description = value }

    var Price: Double
        get() = price
        set(value) { price = value }

    var Stock: Int
        get() = stock
        set(value) { stock = value }

    var Latitude: Int
        get() = latitude
        set(value) { latitude = value }

    var Longitude: Int
        get() = longitude
        set(value) { longitude = value }

    var Photo: Bitmap?
        get() = photo
        set(value) { photo = value }


}