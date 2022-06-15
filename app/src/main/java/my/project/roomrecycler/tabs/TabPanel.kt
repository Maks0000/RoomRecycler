package my.project.roomrecycler.tabs

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import my.project.roomrecycler.R
import my.project.roomrecycler.data.Database
import my.project.roomrecycler.databinding.FragmentTabPanelBinding
import my.project.roomrecycler.repositories.CategoryRepository
import my.project.roomrecycler.repositories.ProductRepository
import my.project.roomrecycler.viewModels.CategoryFactory
import my.project.roomrecycler.viewModels.CategoryViewModel
import my.project.roomrecycler.viewModels.ProductFactory
import my.project.roomrecycler.viewModels.ProductViewModel

class TabPanel : Fragment(), View.OnClickListener, View.OnKeyListener {

    private var binding:FragmentTabPanelBinding? = null
    private var categoryRepository:CategoryRepository? = null
    private var categoryViewModel:CategoryViewModel? = null
    private var categoryFactory:CategoryFactory? = null
    private var productRepository:ProductRepository? = null
    private var productViewModel:ProductViewModel? = null
    private var productFactory: ProductFactory? = null


        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_tab_panel, container, false)

         val categoryDao = Database.getInstance((context as FragmentActivity).application).categoryDAO
         categoryRepository = CategoryRepository(categoryDao)
         categoryFactory = CategoryFactory(categoryRepository!!)
         categoryViewModel = ViewModelProvider(this,categoryFactory!!).get(CategoryViewModel::class.java)

         val productDao = Database.getInstance((context as FragmentActivity).application).productDAO
         productRepository = ProductRepository(productDao)
         productFactory = ProductFactory(productRepository!!)
         productViewModel = ViewModelProvider(this,productFactory!!).get(ProductViewModel::class.java)

         binding?.enterNameProduct?.setOnKeyListener(this)
         binding?.enterCategoryProduct?.setOnKeyListener(this)
         binding?.enterPriceProduct?.setOnKeyListener(this)

         binding?.buttonAddCategoryClothes?.setOnClickListener(this)
         binding?.buttonAddCategoryShoes?.setOnClickListener(this)
         binding?.buttonAddCategoryAccessories?.setOnClickListener(this)
         binding?.buttonAddProduct?.setOnClickListener(this)

         return binding?.root
    }


    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {

            R.id.enterNameProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                   binding?.resEnterNameProduct?.text = binding?.enterNameProduct?.text
                   binding?.enterNameProduct?.setText("")
                    return true
                }
            }

            R.id.enterCategoryProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterCategoryProduct?.text = binding?.enterCategoryProduct?.text
                    binding?.enterCategoryProduct?.setText("")
                    return true
                }
            }

            R.id.enterPriceProduct -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    binding?.resEnterPriceProduct?.text = binding?.enterPriceProduct?.text
                    binding?.enterPriceProduct?.setText("")
                    return true
                }
            }

        }
        return false
    }

    override fun onClick(view : View) {

        when(view.id) {

            R.id.buttonAddCategoryClothes -> {
                categoryViewModel?.startInsert(binding?.buttonAddCategoryClothes?.text?.toString()!!)
            }

            R.id.buttonAddCategoryShoes -> {
                categoryViewModel?.startInsert(binding?.buttonAddCategoryShoes?.text?.toString()!!)
            }

            R.id.buttonAddCategoryAccessories -> {
                categoryViewModel?.startInsert(binding?.buttonAddCategoryAccessories?.text?.toString()!!)
            }

            R.id.buttonAddProduct ->{

                productViewModel?.startInsert(binding?.resEnterNameProduct?.text?.toString()!!,
                binding?.resEnterCategoryProduct?.text?.toString()!!,
                binding?.resEnterPriceProduct?.text?.toString()!!)

                clearResEnterProduct()
            }
        }
    }

    private fun clearResEnterProduct() {
        binding?.resEnterNameProduct?.setText("")
        binding?.resEnterCategoryProduct?.setText("")
        binding?.resEnterPriceProduct?.setText("")
    }


}