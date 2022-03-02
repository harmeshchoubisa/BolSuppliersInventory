package com.bol.retail.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockItemDataSourceTest {

    private val mockDataSource = MockItemDataSource()

    @Test
    fun `should provide a collections of items`() {
        //given

        //when
        val items = mockDataSource.getAllItems()

        //then
        assertThat(items).isNotEmpty
        assertThat(items.size).isGreaterThan(2);

    }

    @Test
    fun `should provide some mock data`() {

        val items = mockDataSource.getAllItems()
        assertThat(items).allMatch { it.itemId.isNotBlank() }

    }

}