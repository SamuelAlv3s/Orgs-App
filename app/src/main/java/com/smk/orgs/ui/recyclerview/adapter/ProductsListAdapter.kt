package com.smk.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.smk.orgs.R
import com.smk.orgs.databinding.ActivityFormProductBinding
import com.smk.orgs.databinding.ProductItemBinding
import com.smk.orgs.extensions.tryToLoadingImage
import com.smk.orgs.model.Product
import java.text.NumberFormat
import java.util.Locale

class ProductsListAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.productFormTitle
        private val description = binding.productFormDescription
        private val amount = binding.productFormAmount
        fun vincule(product: Product) {
            title.text = product.title
            description.text = product.description
            val currencyInstance = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val currencyFormated = currencyInstance.format(product.amount)
            amount.text = currencyFormated

            val visibility = if (product.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.productImageView.visibility = visibility

            binding.productImageView.tryToLoadingImage(product.image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsListAdapter.ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.vincule(product)
    }

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}
