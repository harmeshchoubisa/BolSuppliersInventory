package com.bol.retail.controller

import com.bol.retail.model.Item
import com.bol.retail.service.ItemService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/items")
class ItemController( private val service : ItemService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
    ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping
    fun getAllItems(): Collection<Item> = service.getAllItems()

    @GetMapping("/{id}")
    fun getAnItem(@PathVariable id: String) = service.getAnItem(id)


}