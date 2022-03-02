package com.bol.retail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class RetailApplication

fun main(args: Array<String>) {
	runApplication<RetailApplication>(*args)
}


@RestController
class ItemResource(val service: ItemService) {
	@GetMapping
	fun index(): List<Item> = service.findItems()

	@PostMapping
	fun post(@RequestBody item: Item) {
		service.post(item)
	}

	@DeleteMapping
	fun deleteAllItems() = service.deleteAllItems()
}

@Table("ITEMS")
data class Item(@Id val id: String?, val text: String)

interface ItemRepository : CrudRepository<Item, String> {

	@Query("select * from items")
	fun findItems(): List<Item>
}
