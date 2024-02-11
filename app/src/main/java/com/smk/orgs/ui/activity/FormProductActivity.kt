package com.smk.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.smk.orgs.R
import com.smk.orgs.dao.ProductDao
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
    }

    private fun configureSaveButton() {
        val saveButton: Button = binding.productFormSaveButton
        saveButton.setOnClickListener {
            val newProduct = createNewProduct()
            val dao = ProductDao()

            dao.addProduct(newProduct)
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

        return Product(title, description, amount, image = url)
    }
}