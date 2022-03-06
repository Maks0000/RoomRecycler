package my.project.roomrecycler.repositories

import my.project.roomrecycler.data.CategoryDao
import my.project.roomrecycler.models.CategoryModel

class CategoryRepository(private val categoryDao: CategoryDao) {

    val categories = categoryDao.getAllCategories()

    suspend fun insertCategory(categoryModel: CategoryModel){
        categoryDao.insertCategory(categoryModel)
    }

    suspend fun updateCategory(categoryModel: CategoryModel){
        categoryDao.updateCategory(categoryModel)
    }

    suspend fun deleteCategory(categoryModel: CategoryModel){
        categoryDao.deleteCategory(categoryModel)
    }
    suspend fun deleteAllCategories(){
        categoryDao.deleteAllCategories()
    }
}