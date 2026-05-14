package com.example.architectureproject.data.repository

import android.app.Application
import com.example.architectureproject.data.model.Item
import com.example.architectureproject.data.model.local_db.ItemDao
import com.example.architectureproject.data.model.local_db.ItemDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ItemRepository(application: Application): CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
    private var itemDao: ItemDao
    init {
        val db= ItemDataBase.getDatabase(application.applicationContext)
        itemDao = db?.itemsDao()!!
    }
    fun getItems() = itemDao?.getItems()
    fun addItem(item: Item){
        launch{
            itemDao?.addItem(item)
        }
    }
    fun deleteItem(item:Item){
        launch {
            itemDao?.deleteItem(item)
        }
    }
    suspend fun deleteAll(){
        itemDao?.deleteAll()
    }
    fun getItem(id:Int):Item = itemDao.getItem(id)

}