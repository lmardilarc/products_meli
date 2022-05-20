package com.co.mercadolibre.productsmeli.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.co.mercadolibre.productsmeli.data.model.Picker
import com.co.mercadolibre.productsmeli.databinding.ProductPickersListItemBinding


class ProductPickersAdapter :
    RecyclerView.Adapter<ProductPickersAdapter.ProductPickersViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Picker>() {
        override fun areItemsTheSame(oldItem: Picker, newItem: Picker): Boolean {
            return oldItem.pickerId == newItem.pickerId
        }

        override fun areContentsTheSame(oldItem: Picker, newItem: Picker): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductPickersViewHolder {
        val binding = ProductPickersListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductPickersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductPickersViewHolder, position: Int) {
        val attribute = differ.currentList[position]
        holder.bind(attribute)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ProductPickersViewHolder(private val binding: ProductPickersListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(attribute: Picker) {
            binding.tvTitle.text = attribute.pickerName
            val selectedValue =
                attribute.products.filter { it.tags.contains("selected") }[0].pickerLabel
            binding.tvValue.text = selectedValue
        }
    }
}