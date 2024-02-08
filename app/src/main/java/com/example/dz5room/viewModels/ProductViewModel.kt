package com.example.dz5room.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dz5room.models.ProductModel
import com.example.dz5room.repositories.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel (private val productRepository: ProductRepository) : ViewModel() {

    val products = productRepository.products


    fun getFilter (nameCategory:String, genreProduct:String):
            LiveData<List<ProductModel>> {
        return productRepository.getFilter(nameCategory, genreProduct)
    }

    fun startInsert(nameProduct:String, categoryProduct:String, genreProduct:String) {
        insertProduct(ProductModel(0,nameProduct, categoryProduct, genreProduct))
    }

    fun startUpdateProduct(idProduct:Int, nameProduct:String, nameCategory:String, genreProduct:String) {
        updateProduct(ProductModel(idProduct, nameProduct, nameCategory, genreProduct))
    }

    fun insertProduct(productModel: ProductModel) = viewModelScope.launch{

        productRepository.insertProduct(productModel)
    }

    fun updateProduct(productModel: ProductModel) = viewModelScope.launch{

        productRepository.updateProduct(productModel)
    }

    fun deleteProduct(productModel: ProductModel) = viewModelScope.launch{

        productRepository.deleteProduct(productModel)
    }

    fun deleteAllProducts() = viewModelScope.launch{

        productRepository.deleteAllProducts()
    }


}