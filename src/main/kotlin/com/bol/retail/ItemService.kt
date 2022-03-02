package com.bol.retail;

import org.springframework.stereotype.Service;


@Service
public class ItemService(val db: ItemRepository) {

    fun findItems(): List<Item> = db.findItems()

    fun post(item: Item){
        db.save(item)
    }

    fun deleteAllItems() = db.deleteAll();
}
