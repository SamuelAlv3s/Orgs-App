package com.smk.orgs.extensions

import android.widget.ImageView
import coil.load
import com.smk.orgs.R

fun ImageView.tryToLoadingImage(url: String? = null) {
    load(url) {
        fallback(com.smk.orgs.R.drawable.erro)
        error(com.smk.orgs.R.drawable.erro)
        placeholder(com.smk.orgs.R.drawable.placeholder)
    }
}