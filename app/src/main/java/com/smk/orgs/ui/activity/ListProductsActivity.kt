package com.smk.orgs.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.smk.orgs.R
import com.smk.orgs.dao.ProductDao
import com.smk.orgs.databinding.ActivityListProductsBinding
import com.smk.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ListProductsActivity : AppCompatActivity() {

    private val dao = ProductDao()
    private val adapter by lazy {
        ProductsListAdapter(this, dao.getAllProducts())
    }
    private val binding by lazy {
        ActivityListProductsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureReciclerView()
        configureFab()
        setContentView(binding.root)
//        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.getAllProducts())
    }

    private fun configureFab() {
        val fab: ExtendedFloatingActionButton = binding.productListFloatingActionButton
        fab.setOnClickListener {
            goToFormProductActivity()
        }
    }

    private fun goToFormProductActivity() {
        val intent = Intent(this, FormProductActivity::class.java)
        startActivity(intent)
    }

    private fun configureReciclerView() {
        val recyclerView: RecyclerView = binding.productListRecyclerView
        recyclerView.adapter = adapter
    }
}