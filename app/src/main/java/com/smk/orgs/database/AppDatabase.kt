package com.smk.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smk.orgs.database.converter.Converters
import com.smk.orgs.database.dao.ProductDao
import com.smk.orgs.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        fun instance(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "orgs.db").allowMainThreadQueries().build()
        }
    }
}