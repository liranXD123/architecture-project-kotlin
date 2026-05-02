package com.example.architectureproject.data.repository

import android.app.Application
import com.example.architectureproject.data.model.Item
import com.example.architectureproject.data.model.local_db.ItemDao
import com.example.architectureproject.data.model.local_db.ItemDataBase

class ItemRepository(application: Application) {
    private var itemDao: ItemDao
    init {
        val db= ItemDataBase.getDatabase(application.applicationContext)
        itemDao = db?.itemsDao()!!
    }
    fun getItems() = itemDao?.getItems()
    fun addItem(item: Item){
        itemDao?.addItem(item)
    }
    fun deleteItem(item:Item){
        itemDao?.deleteItem(item)
    }
    fun getItem(id:Int):Item = itemDao.getItem(id)

}