package com.bol.retail.service

import com.bol.retail.datasource.ItemDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ItemServiceTest {

    private val dataSource: ItemDataSource = mockk(relaxed = true)
    private val itemService = ItemService(dataSource)

    @Test
    fun `should call its data source to retrieve items`() {

        itemService.getAllItems()
        verify(exactly = 1) { dataSource.getAllItems()}
    }
}