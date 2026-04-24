package com.example.notesapp_123140046.data.local

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.notesapp_123140046.db.NotesDatabase

object DatabaseProvider {
    private var database: NotesDatabase? = null

    fun getDatabase(context: Context): NotesDatabase {
        return database ?: run {
            val driver = AndroidSqliteDriver(
                schema = NotesDatabase.Schema,
                context = context,
                name = "notes.db"
            )

            NotesDatabase(driver).also {
                database = it
            }
        }
    }
}