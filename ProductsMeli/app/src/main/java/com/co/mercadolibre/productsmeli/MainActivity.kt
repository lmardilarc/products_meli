package com.co.mercadolibre.productsmeli

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.co.mercadolibre.productsmeli.databinding.ActivityMainBinding
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductPickersAdapter
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductPicturesPagerAdapter
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductsSearchAdapter
import com.co.mercadolibre.productsmeli.presentation.viewmodel.ProductsSearchViewModel
import com.co.mercadolibre.productsmeli.presentation.viewmodel.ProductsSearchViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ProductsSearchViewModelFactory

    @Inject
    lateinit var productsAdapter: ProductsSearchAdapter

    @Inject
    lateinit var pickersAdapter: ProductPickersAdapter

    @Inject
    lateinit var viewPagerAdapter: ProductPicturesPagerAdapter
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: ProductsSearchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, factory)[ProductsSearchViewModel::class.java]
        initSearchBar()
    }


    private fun initSearchBar() {
        binding.edtSearch.apply {
            setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    viewModel.getProducts(this.text.toString(), context)
                    hideKeyboard(v)
                    return@OnKeyListener true
                }
                false
            })
        }
        showStartSearchPlaceHolder()
    }

    /**
     * show the progress bar when the details products view its starting
     * @param isProgress define if progress bar it will show
     */
    fun showProgressMode(isProgress: Boolean) {
        if (isProgress) {
            binding.fragmentContainerView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.fragmentContainerView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }

    }

    /**
     * activate the edition mode for search bar
     * @param isActive define if search bar its active or not
     */
    fun activateSearchBar(isActive: Boolean) {
        binding.edtSearch.isEnabled = isActive
    }

    /**
     * show a view before the start to search products
     */
    private fun showStartSearchPlaceHolder() {
            binding.tvPlaceHolderText.visibility = View.VISIBLE
            binding.imgPlaceHolder.visibility = View.VISIBLE
            binding.tvPlaceHolderText.text =
                getString(R.string.type_some_word_to_start_search)
    }

    /**
     * show a view with place holder if the search have not result
     * @param isEmptySearch define if view its will show
     */
    fun showNotResultsPlaceHolder(isEmptySearch: Boolean) {
        if (isEmptySearch) {
            binding.fragmentContainerView.visibility = View.GONE
            binding.tvPlaceHolderText.visibility = View.VISIBLE
            binding.imgPlaceHolder.visibility = View.VISIBLE
            binding.tvPlaceHolderText.text =
                getString(R.string.cannot_find_products)
        } else {
            binding.fragmentContainerView.visibility = View.VISIBLE
            binding.tvPlaceHolderText.visibility = View.GONE
            binding.imgPlaceHolder.visibility = View.GONE
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}