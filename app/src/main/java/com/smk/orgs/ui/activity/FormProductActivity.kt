package com.smk.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.smk.orgs.R
import com.smk.orgs.dao.ProductDao
import com.smk.orgs.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(
    R.layout.activity_form_product
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureSaveButton()
    }

    private fun configureSaveButton() {
        val saveButton: Button = findViewById<Button>(R.id.product_form_save_button)
        saveButton.setOnClickListener {
            val newProduct = createNewProduct()
            val dao = ProductDao()

            dao.addProduct(newProduct)
            finish()
        }
    }

    private fun createNewProduct(): Product {
        val titleField: EditText = findViewById<EditText>(R.id.product_form_title)
        val title = titleField.text.toString()

        val descriptionField: EditText = findViewById<EditText>(R.id.product_form_description)
        val description = descriptionField.text.toString()

        val amountField: EditText = findViewById<EditText>(R.id.product_form_amount)
        val amountToText = amountField.text.toString()
        val amount = if (amountToText.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(amountToText)
        }

        return Product(title, description, amount)
    }
}