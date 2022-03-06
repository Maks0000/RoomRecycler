package my.project.roomrecycler.repositories

import androidx.lifecycle.LiveData
import androidx.room.Dao
import my.project.roomrecycler.data.ProductDao
import my.project.roomrecycler.models.ProductModel


class ProductRepository(private val productDao: ProductDao) {
    val products = productDao.getAllProducts()

    fun getFilter(nameCategory:String, priceProduct:String): LiveData<List<ProductModel>>{
        return productDao.getFilter(nameCategory,priceProduct)
    }

    suspend fun insertProduct(productModel: ProductModel){
        productDao.insertProduct(productModel)
    }

    suspend fun updateProduct(productModel: ProductModel){
        productDao.updateProduct(productModel)
    }

    suspend fun deleteProduct(productModel: ProductModel){
        productDao.deleteProduct(productModel)
    }

    suspend fun deleteAllProduct(){
        productDao.deleteAllProducts()
    }

}