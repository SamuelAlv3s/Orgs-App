package com.smk.orgs.model

import java.math.BigDecimal

data class Product(
    val title: String,
    val description: String,
    val amount: BigDecimal,
    val image: String? = null
) {

}
