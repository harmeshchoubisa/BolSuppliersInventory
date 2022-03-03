package com.bol.retail.controller

import com.bol.retail.model.Item
import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class ItemControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/items"

    @Nested
    @DisplayName("getItems()")
    @TestInstance(PER_CLASS)
    inner class GetAllItems {
        @Test
        fun `should return all items`() {

            mockMvc.get(baseUrl)
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
            mockMvc.get("$baseUrl/$itemId")
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

        @Test
        fun `should return NOT FOUND if an item id is not present`() {
            val itemId = "does_not_exist"
            mockMvc.get("$baseUrl/$itemId")
                    .andDo { print() }
                    .andExpect {
                        status { isNotFound() }
                    }
        }
    }

    @Nested
    @DisplayName("Add an item")
    @TestInstance(PER_CLASS)
    inner class PostNewItem {

        @Test
        fun `should add a new item`() {

            val newItem = Item("114", "Television", "Philips", 400.34, 755.67)

            val performPost = mockMvc.post(baseUrl) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(newItem)
            }

            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                        content { contentType(APPLICATION_JSON) }
                        jsonPath("$.itemId") {value("114")}
                        jsonPath("$.itemName") {value("Television")}
                        jsonPath("$.supplierName") {value("Philips")}
                        jsonPath("$.supplierPrice") {value("400.34")}
                        jsonPath("$.consumerPrice") {value("755.67")}
                    }
        }

        @Test
        fun `should return BAD REQUEST if item with given id already exists`() {
            val invalidItem = Item("111", "doesNotMatter","doesNotMatter",100.56,455.56)

            val performPost = mockMvc.post(baseUrl) {
                contentType = APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidItem)
            }

            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isBadRequest() }
                    }

        }
    }

}

