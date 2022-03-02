package com.bol.retail.datasource.mock

import com.bol.retail.datasource.ItemDataSource
import com.bol.retail.model.Item
import org.springframework.stereotype.Repository

@Repository
class MockItemDataSource : ItemDataSource {

    val items = listOf(
            Item("111", "Macbook", "CoolBlue", 1000.55, 1500.55),
            Item("112", "Lenovo", "Amazon", 500.55, 600.55),
            Item("113", "Asus", "MediaMarket", 700.55, 800.55),
    )

    override fun getAllItems(): Collection<Item> = items

    override fun getAnItem(id: String): Item = items.first{ it.itemId == id }

}