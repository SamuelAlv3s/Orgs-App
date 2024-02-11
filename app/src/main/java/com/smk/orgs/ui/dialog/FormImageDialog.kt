package com.smk.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.smk.orgs.databinding.FormImageBinding
import com.smk.orgs.extensions.tryToLoadingImage

class FormImageDialog(private val context: Context) {

    fun show(defaultUrl: String? = null, whenLoadedImage: (image: String) -> Unit) {
        FormImageBinding.inflate(LayoutInflater.from(context)).apply {
            defaultUrl?.let {
                formImageImageView.tryToLoadingImage(it)
                formImageInput.setText(it)
            }

            formImageButton.setOnClickListener {
                val url = formImageInput.text.toString()
                formImageImageView.tryToLoadingImage(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formImageInput.text.toString()
                    whenLoadedImage(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }
}