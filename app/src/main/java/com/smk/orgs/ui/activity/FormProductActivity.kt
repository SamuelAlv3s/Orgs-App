package com.smk.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import coil.load
import com.smk.orgs.R
import com.smk.orgs.dao.ProductDao
import com.smk.orgs.database.AppDatabase
import com.smk.orgs.databinding.ActivityFormProductBinding
import com.smk.orgs.databinding.ActivityListProductsBinding
import com.smk.orgs.databinding.FormImageBinding
import com.smk.orgs.extensions.tryToLoadingImage
import com.smk.orgs.model.Product
import com.smk.orgs.ui.dialog.FormImageDialog
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }

    private var url: String? = null
    private var productId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configureSaveButton()
        binding.productFormImage.setOnClickListener {
            FormImageDialog(this).show(url) { image ->
                url = image
                binding.productFormImage.tryToLoadingImage((url))
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra<Product>(CHAVE_PRODUTO, Product::class.java)?.let { loadedProduct ->
                title = "Alterar produto"
                productId = loadedProduct.id
                url = loadedProduct.image
                binding.productFormImage.tryToLoadingImage(loadedProduct.image)
                binding.productFormTitle.setText(loadedProduct.title)
                binding.productFormDescription.setText(loadedProduct.description)
                binding.productFormAmount.setText(loadedProduct.amount.toPlainString())
            }
        } else {
            intent.getParcelableExtra<Product>(CHAVE_PRODUTO)?.let { loadedProduct ->
                title = "Alterar produto"
                productId = loadedProduct.id
                url = loadedProduct.image
                binding.productFormImage.tryToLoadingImage(loadedProduct.image)
                binding.productFormTitle.setText(loadedProduct.title)
                binding.productFormDescription.setText(loadedProduct.description)
                binding.productFormAmount.setText(loadedProduct.amount.toPlainString())
            }
        }
    }

    private fun configureSaveButton() {
        val saveButton: Button = binding.productFormSaveButton
        val db = AppDatabase.instance(this)
        val productDao = db.productDao()
        saveButton.setOnClickListener {
            val newProduct = createNewProduct()

            productDao.saveProduct(newProduct)

            finish()
        }
    }

    private fun createNewProduct(): Product {
        val titleField: EditText = binding.productFormTitle
        val title = titleField.text.toString()

        val descriptionField: EditText = binding.productFormDescription
        val description = descriptionField.text.toString()

        val amountField: EditText = binding.productFormAmount
        val amountToText = amountField.text.toString()
        val amount = if (amountToText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(amountToText)
        }

        return Product(id = productId, title = title, description = description, amount = amount, image = url)
    }
}