package com.example.notesapp_123140046.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp_123140046.data.settings.SettingsManager
import com.example.notesapp_123140046.repository.NoteRepository

class NotesViewModelFactory(
    private val repository: NoteRepository,
    private val settingsManager: SettingsManager
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(repository, settingsManager) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}