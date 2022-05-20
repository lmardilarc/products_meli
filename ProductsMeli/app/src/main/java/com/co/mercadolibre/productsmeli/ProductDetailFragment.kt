package com.co.mercadolibre.productsmeli

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.co.mercadolibre.productsmeli.data.util.Resource
import com.co.mercadolibre.productsmeli.databinding.FragmentProductDetailBinding
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductPickersAdapter
import com.co.mercadolibre.productsmeli.presentation.adapter.ProductPicturesPagerAdapter
import com.co.mercadolibre.productsmeli.presentation.viewmodel.ProductsSearchViewModel


class ProductDetailFragment : Fragment() {

    private lateinit var viewPagerAdapter: ProductPicturesPagerAdapter
    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var viewModel: ProductsSearchViewModel
    private lateinit var pickersAdapter: ProductPickersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        viewPagerAdapter = (activity as MainActivity).viewPagerAdapter
        pickersAdapter = (activity as MainActivity).pickersAdapter
        (requireActivity() as MainActivity).activateSearchBar(false)
        initPickersRecyclerView()
        observeProductDetail()

    }

    private fun observeProductDetail() {
        viewModel.productDetail.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    (requireActivity() as MainActivity).showProgressMode(false)
                    response.data?.let {
                        showProduct()
                        pickersAdapter.differ.submitList(it.pickers)
                        initViewPager()
                    }
                }
                is Resource.Error -> {
                    (requireActivity() as MainActivity).showProgressMode(false)
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.an_error_has_ocurred_try_onece),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.Loading -> {
                    (requireActivity() as MainActivity).showProgressMode(true)
                }
            }
        }
    }

    private fun showProduct() {
        viewModel.productDetail.value?.data?.apply {
            binding.tvTitle.text = name
            binding.tvDescription.text = shortDescription.content
            binding.tvFeaturesList.text = viewModel.concatFeaturesList()
        }
    }

    private fun initViewPager() {
        viewPagerAdapter.setPicturesList(
            viewModel.productDetail.value?.data?.pictures ?: emptyList()
        )
        binding.vpgrPhotos.adapter = viewPagerAdapter
    }

    private fun initPickersRecyclerView() {
        pickersAdapter = ProductPickersAdapter()
        binding.rcvPickers.apply {
            adapter = pickersAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}