package com.example.dz5room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dz5room.models.CategoryModel
import com.example.dz5room.models.ProductModel

@Database(entities = [CategoryModel::class, ProductModel::class],version = 1)
abstract class Database: RoomDatabase() {

    abstract val productDAO : ProductDao
    abstract val categoryDAO : CategoryDao

    companion object{
        @Volatile
        private var INSTANCE : com.example.dz5room.data.Database? = null
        fun getInstance(context: Context):com.example.dz5room.data.Database{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        com.example.dz5room.data.Database::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }

    }
}