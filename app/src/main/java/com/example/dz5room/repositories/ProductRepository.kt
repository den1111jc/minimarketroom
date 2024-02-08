package com.example.dz5room.repositories

import androidx.lifecycle.LiveData
import com.example.dz5room.data.ProductDao
import com.example.dz5room.models.ProductModel

class ProductRepository (private val productDAO: ProductDao) {

    val products = productDAO.getAllProducts()

    fun getFilter (nameCategory:String, genreProduct:String):
            LiveData<List<ProductModel>> {
        return productDAO.getFilter(nameCategory, genreProduct)
    }


    suspend fun insertProduct(productModel: ProductModel){
        productDAO.insertProduct(productModel)
    }

    suspend fun updateProduct(productModel: ProductModel){
        productDAO.updateProduct(productModel)
    }

    suspend fun deleteProduct(productModel: ProductModel) {
        productDAO.deleteProduct(productModel)
    }

    suspend fun deleteAllProducts(){
        productDAO.deleteAllProducts()
    }
}