package my.project.roomrecycler.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.project.roomrecycler.repositories.CategoryRepository

class CategoryFactory (private val categoryRepository: CategoryRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            return CategoryViewModel(categoryRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}