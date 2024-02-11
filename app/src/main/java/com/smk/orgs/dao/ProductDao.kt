package com.smk.orgs.dao

import com.smk.orgs.model.Product
import java.math.BigDecimal

class ProductDao {

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun getAllProducts(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product("Fruits", "Description about...", BigDecimal("25.00"))
        )
    }
}