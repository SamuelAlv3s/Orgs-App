package com.smk.orgs.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.smk.orgs.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAll(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProduct(vararg product: Product)

    @Delete
    fun removeProduct(vararg product: Product)

    @Update
    fun updateProduct(vararg product: Product)

    @Query("SELECT * FROM Product WHERE id = :id")
    abstract fun getById(id: Long) : Product?
}