package com.bol.retail.datasource.mock

import com.bol.retail.datasource.ItemDataSource
import com.bol.retail.model.Item
import org.springframework.stereotype.Repository

@Repository
class MockItemDataSource : ItemDataSource {

    val items = mutableListOf(
            Item("111", "Macbook", "CoolBlue", 1000.55, 1500.55),
            Item("112", "Lenovo", "Amazon", 500.55, 600.55),
            Item("113", "Asus", "MediaMarket", 700.55, 800.55),
    )

    override fun getAllItems(): Collection<Item> = items

    override fun getAnItem(id: String): Item = items.firstOrNull() { it.itemId == id }
            ?: throw NoSuchElementException("Could not find an item with item id $id")

    override fun createItem(item: Item): Item {
        if (items.any { it.itemId == item.itemId }) {
            throw IllegalArgumentException("Item with item id $item.itemId already exists")
        }
        items.add(item)
        return item
    }

    override fun updateItem(item: Item): Item {
        val currentItem = items.firstOrNull() { it.itemId == item.itemId }
            ?: throw NoSuchElementException("Could not find an item with item id ${item.itemId}")

        items.remove(currentItem)
        items.add(item)
        return item
    }

    override fun deleteItem(id: String) {
        val currentItem = items.firstOrNull() { it.itemId == id }
            ?: throw NoSuchElementException("Could not find an item with item id $id}")

        items.remove(currentItem)
    }

}