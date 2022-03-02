package com.bol.retail.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class ItemControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("getItems()")
    @TestInstance(PER_CLASS)
    inner class GetAllItems {
        @Test
        fun `should return all items`() {

            mockMvc.get("/api/items")
                    .andDo { print() }
                    .andExpect {
                        status {
                            isOk()
                            content { contentType(APPLICATION_JSON) }
                        }
                        jsonPath("$[0].itemId") { value("111") }
                    }

        }
    }

    @Nested
    @DisplayName("getItem()")
    @TestInstance(PER_CLASS)
    inner class GetAnItems {
        @Test
        fun `should return the item with given item id`() {
            val itemId = 111
            mockMvc.get("/api/items/$itemId")
                    .andDo { print() }
                    .andExpect {
                        status {
                            isOk()
                            content { contentType(APPLICATION_JSON) }
                        }
                        jsonPath("$.supplierPrice") { value("1000.55") }
                        jsonPath("$.itemName") { value("Macbook") }
                    }
        }
    }

}

