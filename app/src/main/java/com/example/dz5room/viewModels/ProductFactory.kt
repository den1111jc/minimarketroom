package com.example.dz5room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dz5room.repositories.ProductRepository

class ProductFactory constructor(private val productRepository: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(this.productRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}