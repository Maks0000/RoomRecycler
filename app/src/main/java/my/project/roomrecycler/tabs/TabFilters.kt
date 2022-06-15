package my.project.roomrecycler.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import my.project.roomrecycler.R
import my.project.roomrecycler.data.Database
import my.project.roomrecycler.databinding.FragmentTabFiltersBinding
import my.project.roomrecycler.models.ProductModel
import my.project.roomrecycler.repositories.ProductRepository
import my.project.roomrecycler.viewModels.ProductFactory
import my.project.roomrecycler.viewModels.ProductViewModel


class TabFilters : Fragment() {
    private var binding: FragmentTabFiltersBinding? = null
    private var productRepository: ProductRepository? = null
    private var productViewModel: ProductViewModel? = null
    private var productFactory: ProductFactory? = null
    private var productAdapter: ProductAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tab_filters, container, false)

        val productDao = Database.getInstance((context as FragmentActivity).application).productDAO
        productRepository = ProductRepository(productDao)
        productFactory = ProductFactory(productRepository!!)
        productViewModel = ViewModelProvider(this,productFactory!!).get(ProductViewModel::class.java)
        initRecyclerFilterProducts()

        return binding?.root
    }

    private fun initRecyclerFilterProducts(){
        binding?.recyclerFilter?.layoutManager = LinearLayoutManager(context)
        productAdapter = ProductAdapter({productModel: ProductModel -> deleteProduct(productModel)},
            {productModel: ProductModel -> editProduct(productModel)})
        binding?.recyclerFilter?.adapter = productAdapter
        displayFilterProducts()
    }

    private fun displayFilterProducts(){
        productViewModel?.getFilter("clothes")?.observe(viewLifecycleOwner, Observer {
            productAdapter?.setList(it)
            productAdapter?.notifyDataSetChanged()
        })
    }

    private fun deleteProduct(productModel: ProductModel) {
        productViewModel?.deleteProduct(productModel)
    }

    private fun editProduct(productModel:ProductModel){
        val panelEditProduct = PanelEditProduct()
        val parameters = Bundle()
        parameters.putString("idProduct", productModel.id.toString())
        parameters.putString("nameProduct", productModel.name)
        parameters.putString("categoryProduct", productModel.category)
        parameters.putString("priceProduct",productModel.price)
        panelEditProduct.arguments = parameters

        panelEditProduct.show((context as FragmentActivity).supportFragmentManager,"editProduct")
    }
}
