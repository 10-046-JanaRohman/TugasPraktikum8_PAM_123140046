package com.example.notesapp_123140046.model

data class NoteModel(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long
)