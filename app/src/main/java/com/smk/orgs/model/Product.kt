package com.smk.orgs.model

import android.os.Parcelable
import java.math.BigDecimal
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val title: String,
    val description: String,
    val amount: BigDecimal,
    val image: String? = null
) : Parcelable
