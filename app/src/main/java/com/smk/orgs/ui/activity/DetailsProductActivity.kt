package com.smk.orgs.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.smk.orgs.R
import com.smk.orgs.database.AppDatabase
import com.smk.orgs.databinding.ActivityDetailsProductBinding
import com.smk.orgs.extensions.formatToBRLCurrency
import com.smk.orgs.extensions.tryToLoadingImage
import com.smk.orgs.model.Product

class DetailsProductActivity : AppCompatActivity() {

    private var productId: Long? = null
    private var product: Product? = null
    private val binding by lazy {
        ActivityDetailsProductBinding.inflate(layoutInflater)
    }

    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryToLoadProduct()
    }

    override fun onResume() {
        super.onResume()
        productId?.let { id ->
            product = productDao.getById(id)
        }
        product?.let {
            fillInputs(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menu_details_product_edit -> {
                Intent(this, FormProductActivity::class.java).apply {
                    putExtra(CHAVE_PRODUTO, product)
                    startActivity(this)
                }
            }

            R.id.menu_details_product_delete -> {
                product?.let {
                    productDao.removeProduct(it)
                }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryToLoadProduct() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Product>(CHAVE_PRODUTO, Product::class.java)
                ?.let { productLoaded ->
                    productId = productLoaded.id
                } ?: finish()
        } else {
            intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let { productLoaded ->
                productId = productLoaded.id
            } ?: finish()
        }
    }

    private fun fillInputs(productLoaded: Product) {
        with(binding) {
            activityDetalhesProdutoImagem.tryToLoadingImage(productLoaded.image)
            activityDetalhesProdutoNome.text = productLoaded.title
            activityDetalhesProdutoDescricao.text = productLoaded.description
            activityDetalhesProdutoValor.text =
                productLoaded.amount.formatToBRLCurrency()
        }
    }

}