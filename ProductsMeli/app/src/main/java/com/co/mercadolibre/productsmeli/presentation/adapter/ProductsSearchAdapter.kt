package com.co.mercadolibre.productsmeli.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.co.mercadolibre.productsmeli.R
import com.co.mercadolibre.productsmeli.data.model.Result
import com.co.mercadolibre.productsmeli.databinding.ProductsSearchListItemBinding
import com.co.mercadolibre.productsmeli.domain.Utils

class ProductsSearchAdapter :
    RecyclerView.Adapter<ProductsSearchAdapter.ProductsSearchViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnProductListener(onProductListener: (Result) -> Unit) {
        this.onProductListener = onProductListener
    }

    private lateinit var onProductListener: (Result) -> Unit
    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsSearchViewHolder {
        val binding = ProductsSearchListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductsSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsSearchViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ProductsSearchViewHolder(private val binding: ProductsSearchListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.tvTitle.text = result.title
            binding.tvPrice.text = Utils.getFormatCurrency(result.price)
            binding.tvQuotes.text = String.format(
                binding.tvQuotes.context.resources.getString(
                    R.string.interests_quotes
                ),
                result.installments.quantity.toString(),
                Utils.getFormatCurrency(result.installments.amount)
            )
            binding.tvAvailableQuantity.text = String.format(
                binding.tvQuotes.context.resources.getString(R.string.available_quantity),
                result.availableQuantity
            )
            binding.lytItem.setOnClickListener { onProductListener(result) }
            Glide.with(binding.imageView.context)
                .load(result.thumbnail.replace("http:", "https:"))
                .fitCenter()
                .into(binding.imageView)
        }

    }
}