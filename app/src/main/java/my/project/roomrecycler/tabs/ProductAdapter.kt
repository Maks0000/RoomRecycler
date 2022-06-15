package my.project.roomrecycler.tabs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import my.project.roomrecycler.R
import my.project.roomrecycler.databinding.CategoryItemBinding
import my.project.roomrecycler.databinding.ProductItemBinding
import my.project.roomrecycler.models.CategoryModel
import my.project.roomrecycler.models.ProductModel

class ProductAdapter(private val deleteCategory:(ProductModel)->Unit,
                     private val editCategory:(ProductModel)->Unit): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    private val productsList = ArrayList<ProductModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.product_item,parent,false)
        return ProductHolder(binding)

    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bind(productsList[position],deleteCategory, editCategory)
    }

    fun setList(products: List<ProductModel>){
        productsList.clear()
        productsList.addAll(products)
    }

    class ProductHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            productsModel: ProductModel,
            deleteProduct: (ProductModel) -> Unit,
            editProduct: (ProductModel) -> Unit
        ){
            binding.idProduct.text = productsModel.id.toString()
            binding.nameProduct.text = productsModel.name
            binding.categoryProduct.text = productsModel.category
            binding.priceProduct.text = productsModel.price

            binding.editProduct.setOnClickListener(View.OnClickListener {
                editProduct(productsModel)
            })

            binding.deleteProduct.setOnClickListener(View.OnClickListener {
                deleteProduct(productsModel)
            })
        }
    }
}