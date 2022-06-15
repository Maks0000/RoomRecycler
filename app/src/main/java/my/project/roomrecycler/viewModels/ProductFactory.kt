package my.project.roomrecycler.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.project.roomrecycler.repositories.ProductRepository

class ProductFactory (private val productRepository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)){
            return ProductViewModel(productRepository) as T

        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}