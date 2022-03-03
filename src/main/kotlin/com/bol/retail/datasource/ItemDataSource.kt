package com.bol.retail.datasource

import com.bol.retail.model.Item

interface ItemDataSource {

    fun getAllItems(): Collection<Item>
    fun getAnItem(id: String): Item
    fun createItem(item: Item): Item
    fun updateItem(item: Item): Item
    fun deleteItem(id: String)

}