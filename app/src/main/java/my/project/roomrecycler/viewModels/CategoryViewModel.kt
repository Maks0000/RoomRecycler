package my.project.roomrecycler.viewModels

import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.project.roomrecycler.models.CategoryModel
import my.project.roomrecycler.repositories.CategoryRepository

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    val categories = categoryRepository.categories

    fun startInsert(nameCategories:String){
        insert(CategoryModel(0, nameCategories))
    }

    fun startUpdateProduct(idCategories:Int, nameCategories:String){
        updateProduct(CategoryModel(idCategories,nameCategories))
    }

    fun insert(categoryModel: CategoryModel) = viewModelScope.launch{
        categoryRepository.insertCategory(categoryModel)
    }

    fun updateProduct(categoryModel : CategoryModel) = viewModelScope.launch{
        categoryRepository.updateCategory(categoryModel)
    }

    fun delete(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryRepository.deleteCategory(categoryModel)
    }

    fun deleteAllCategories(categoryModel: CategoryModel) = viewModelScope.launch {
        categoryRepository.deleteAllCategories()
    }

}