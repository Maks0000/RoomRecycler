package my.project.roomrecycler.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Delete
import my.project.roomrecycler.R
import my.project.roomrecycler.databinding.CategoryItemBinding
import my.project.roomrecycler.models.CategoryModel

class CategoryAdapter(private val deleteCategory:(CategoryModel)->Unit,
                      private val editCategory:(CategoryModel)->Unit): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private val categoriesList = ArrayList<CategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: CategoryItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.category_item,parent,false)

        return CategoryHolder(binding)

    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoriesList[position], deleteCategory, editCategory)
    }

    fun setList(categories: List<CategoryModel>){
        categoriesList.clear()
        categoriesList.addAll(categories)
    }

    class CategoryHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            categories: CategoryModel,
            deleteCategory:(CategoryModel) ->Unit,
            editCategory:(CategoryModel) ->Unit,
        ){
            binding.idCategory.text = categories.id.toString()
            binding.nameCategory.text = categories.name

            binding.editCategory.setOnClickListener(View.OnClickListener {
                editCategory(categories)
            })

            binding.deleteCategory.setOnClickListener(View.OnClickListener {
                deleteCategory(categories)
            })
        }
    }
}