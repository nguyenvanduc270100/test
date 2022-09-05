/*
 *  Created by Nguyễn Kim Khánh on 15:50, 08/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 15:50, 08/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bangcodin.alldocumentreader.db.dao.DocumentDao
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [DocumentItem::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun documentDao(): DocumentDao

    private class DatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    //init data in here
                    val documentDao = database.documentDao()

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "app_db"
                ).addCallback(DatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}