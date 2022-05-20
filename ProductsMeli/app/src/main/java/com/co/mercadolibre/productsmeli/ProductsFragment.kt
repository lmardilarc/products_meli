package com.co.mercadolibre.productsmeli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.co.mercadolibre.productsmeli.data.util.Resource
import com.co.mercadolibre.productsmeli.databinding.FragmentProductsBinding
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductsSearchAdapter
import com.co.mercadolibre.productsmeli.presentation.viewmodel.ProductsSearchViewModel
import com.co.mercadolibre.productsmeli.data.model.Result

class ProductsFragment : Fragment() {
    private lateinit var productsAdapter: ProductsSearchAdapter
    private lateinit var binding: FragmentProductsBinding
    private lateinit var viewModel: ProductsSearchViewModel
    private var isScrolling: Boolean = false
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        productsAdapter = (activity as MainActivity).productsAdapter
        initRecyclerView()
        observeSearchList()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).activateSearchBar(true)
    }

    private fun observeSearchList() {
        viewModel.productsList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    isLoading = false
                    response.data?.let {
                        productsAdapter.differ.submitList(it.results.toList())
                        showEmptySearch(it.results.toList().size)
                    }
                }
                is Resource.Error -> {
                    isLoading = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.an_error_has_ocurred_try_onece),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                    isLoading = true
                }
            }
        }
    }

    private fun showEmptySearch(searchSize: Int) {
        (requireActivity() as MainActivity).showNotResultsPlaceHolder(searchSize == 0)
    }

    private fun onProductClick(product: Result) {
        if (product.catalogProductId != null) {
            requireView().findNavController()
                .navigate(R.id.action_productsFragment_to_productDetailFragment)
            viewModel.getProductDetail(product.catalogProductId, requireContext())
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.cannot_download_product_detail),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }


    private fun initRecyclerView() {
        productsAdapter.setOnProductListener { product: Result ->
            onProductClick(product)
        }
        binding.rcvProducts.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@ProductsFragment.onScrollListener)
        }
    }


    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            isScrolling = newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rcvProducts.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()
            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate =
                !isLoading && hasReachedToEnd && viewModel.productsList.value?.data?.results?.isNotEmpty() ?: false

            if (shouldPaginate) {
                viewModel.getNextPage()
                isScrolling = false
            }

        }
    }
}