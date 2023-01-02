package id.daffarandika.restaurantmanagement.model

data class Detailorder(
    val detailid: String,
    val orderid: String,
    val menuid: String,
    val qty: String,
    val price: String,
    val status: String
)
