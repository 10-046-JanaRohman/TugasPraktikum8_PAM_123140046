package com.example.notesapp_123140046.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.notesapp_123140046.db.NotesDatabase
import com.example.notesapp_123140046.model.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NoteRepository(
    private val database: NotesDatabase
) {
    private val queries = database.noteQueries

    private fun mapToNoteModel(
        id: Long,
        title: String,
        content: String,
        created_at: Long,
        updated_at: Long
    ): NoteModel {
        return NoteModel(
            id = id,
            title = title,
            content = content,
            createdAt = created_at,
            updatedAt = updated_at
        )
    }

    fun getAllNotes(sortOrder: String): Flow<List<NoteModel>> {
        return if (sortOrder == "oldest") {
            queries.selectAllOldest(::mapToNoteModel)
                .asFlow()
                .mapToList(Dispatchers.IO)
        } else {
            queries.selectAllNewest(::mapToNoteModel)
                .asFlow()
                .mapToList(Dispatchers.IO)
        }
    }

    fun searchNotes(keyword: String): Flow<List<NoteModel>> {
        val query = "%$keyword%"

        return queries.searchNotes(query, query, ::mapToNoteModel)
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    suspend fun getNoteById(id: Long): NoteModel? {
        return withContext(Dispatchers.IO) {
            queries.selectById(id, ::mapToNoteModel).executeAsOneOrNull()
        }
    }

    suspend fun insertNote(title: String, content: String) {
        val now = System.currentTimeMillis()

        withContext(Dispatchers.IO) {
            queries.insertNote(
                title = title,
                content = content,
                created_at = now,
                updated_at = now
            )
        }
    }

    suspend fun updateNote(id: Long, title: String, content: String) {
        val now = System.currentTimeMillis()

        withContext(Dispatchers.IO) {
            queries.updateNote(
                title = title,
                content = content,
                updated_at = now,
                id = id
            )
        }
    }

    suspend fun deleteNote(id: Long) {
        withContext(Dispatchers.IO) {
            queries.deleteNote(id)
        }
    }
}