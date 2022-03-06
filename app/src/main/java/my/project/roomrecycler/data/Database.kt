package my.project.roomrecycler.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import my.project.roomrecycler.models.CategoryModel
import my.project.roomrecycler.models.ProductModel

@Database(entities = [CategoryModel::class, ProductModel::class],version = 1)
abstract class Database: RoomDatabase() {

    abstract val productDao : ProductDao
    abstract val categoryDao : CategoryDao

    companion object {
        @Volatile
        private var INSTANCE : my.project.roomrecycler.data.Database? = null
        fun getInstance(context: Context):my.project.roomrecycler.data.Database{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        my.project.roomrecycler.data.Database::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }
    }
}