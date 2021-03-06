package com.bol.retail.service

import com.bol.retail.datasource.ItemDataSource
import com.bol.retail.model.Item
import org.springframework.stereotype.Service

@Service
class ItemService (private val dataSource: ItemDataSource){
    fun getAllItems(): Collection<Item> = dataSource.getAllItems()

    fun getAnItem(id: String): Item = dataSource.getAnItem(id)
    fun addItem(item: Item): Item = dataSource.createItem(item)
    fun patchItem(item: Item): Item = dataSource.updateItem(item)
    fun deleteItem(id: String): Unit = dataSource.deleteItem(id)

}