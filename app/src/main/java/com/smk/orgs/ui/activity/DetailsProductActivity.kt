package com.smk.orgs.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smk.orgs.databinding.ActivityDetailsProductBinding
import com.smk.orgs.extensions.formatToBRLCurrency
import com.smk.orgs.extensions.tryToLoadingImage
import com.smk.orgs.model.Product

class DetailsProductActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailsProductBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryToLoadProduct()
    }

    private fun tryToLoadProduct() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Product>(CHAVE_PRODUTO, Product::class.java)?.let { productLoaded ->
                fillInputs(productLoaded)
            } ?: finish()
        } else {
            intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let { productLoaded ->
                fillInputs(productLoaded)
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