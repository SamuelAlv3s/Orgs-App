package com.smk.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.smk.orgs.R
import com.smk.orgs.dao.ProductDao
import com.smk.orgs.databinding.ActivityFormProductBinding
import com.smk.orgs.databinding.ActivityListProductsBinding
import com.smk.orgs.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureSaveButton()
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

        return Product(title, description, amount)
    }
}