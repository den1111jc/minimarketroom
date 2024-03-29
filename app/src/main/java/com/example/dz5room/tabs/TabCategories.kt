package com.example.dz5room.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dz5room.databinding.TabCategoriesBinding
import com.example.dz5room.models.CategoryModel
import com.example.dz5room.repositories.CategoryRepository
import com.example.dz5room.viewModels.CategoryFactory
import com.example.dz5room.viewModels.CategoryViewModel
import com.example.dz5room.data.Database

class TabCategories : Fragment() {

    private var binding: TabCategoriesBinding? = null
    private var categoryRepository: CategoryRepository? = null
    private var categoryViewModel: CategoryViewModel? = null
    private var categoryFactory: CategoryFactory? = null
    private var categoryAdapter: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = TabCategoriesBinding.inflate(inflater, container, false)

        val categoriesDao = Database.getInstance((context as FragmentActivity).application).categoryDAO
        categoryRepository = CategoryRepository(categoriesDao)
        categoryFactory = CategoryFactory(categoryRepository!!)
        categoryViewModel = ViewModelProvider(this, categoryFactory!!).get(CategoryViewModel::class.java)

        initRecyclerCategories()
        displayCategories()

        binding?.deleteAllCategories?.setOnClickListener(View.OnClickListener {
            categoryViewModel?.deleteAll()
        })

        return  binding?.root
    }

    private fun initRecyclerCategories(){
        binding?.recyclerCategories?.layoutManager = LinearLayoutManager(context)
        categoryAdapter = CategoryAdapter({categoryModel: CategoryModel ->deleteCategory(categoryModel)},
            {categoryModel:CategoryModel->editCategory(categoryModel)})
        binding?.recyclerCategories?.adapter = categoryAdapter


    }

    private fun displayCategories(){
        categoryViewModel?.categories?.observe(viewLifecycleOwner, Observer {
            categoryAdapter?.setList(it)  // будет записывать все необх данные в рец вью
            categoryAdapter?.notifyDataSetChanged() //обновляет адаптер
        })
    }


    private fun deleteCategory(categoryModel: CategoryModel) {
        categoryViewModel?.delete(categoryModel)
    }

    private fun editCategory(categoryModel:CategoryModel) {
        val panelCategory = PanelEditCategory()
        val parameters = Bundle()
        parameters.putString("idCategory", categoryModel.id.toString())
        parameters.putString("nameCategory", categoryModel.name)
        panelCategory.arguments = parameters

        panelCategory.show((context as FragmentActivity).supportFragmentManager, "editCategory")
    }


}