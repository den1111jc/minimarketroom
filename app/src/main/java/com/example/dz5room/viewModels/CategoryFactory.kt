package com.example.dz5room.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dz5room.repositories.CategoryRepository

class CategoryFactory constructor(private val categoryRepository: CategoryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            CategoryViewModel(this.categoryRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}