package com.bol.retail.model

data class Item (

    val itemId: String,
    val itemName: String,
    val supplierName: String,
    val supplierPrice: Double,
    val consumerPrice: Double
)