package com.smk.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.smk.orgs.R
import com.smk.orgs.dao.ProductDao
import com.smk.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ListProductsActivity : AppCompatActivity(
    R.layout.activity_list_products
) {

    private val dao = ProductDao()
    private val adapter = ProductsListAdapter(this, dao.getAllProducts())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureReciclerView()
        configureFab()
//        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.getAllProducts())
    }

    private fun configureFab() {
        val fab: FloatingActionButton =
            findViewById<FloatingActionButton>(R.id.product_list_floatingActionButton)
        fab.setOnClickListener {
            goToFormProductActivity()
        }
    }

    private fun goToFormProductActivity() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }

    private fun configureReciclerView() {
        val dao = ProductDao()
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.product_list_recyclerView)
        recyclerView.adapter = adapter
    }
}