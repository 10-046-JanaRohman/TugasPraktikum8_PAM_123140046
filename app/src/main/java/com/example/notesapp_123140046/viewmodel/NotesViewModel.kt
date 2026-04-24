package com.example.notesapp_123140046.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp_123140046.data.settings.SettingsManager
import com.example.notesapp_123140046.model.NoteModel
import com.example.notesapp_123140046.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class NotesUiState(
    val isLoading: Boolean = false,
    val notes: List<NoteModel> = emptyList(),
    val searchQuery: String = "",
    val isEmpty: Boolean = false
)

@OptIn(ExperimentalCoroutinesApi::class)
class NotesViewModel(
    private val repository: NoteRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    val theme: StateFlow<String> = settingsManager.themeFlow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            "system"
        )

    val sortOrder: StateFlow<String> = settingsManager.sortOrderFlow
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            "newest"
        )

    val uiState: StateFlow<NotesUiState> =
        combine(_searchQuery, sortOrder) { query, sort ->
            Pair(query, sort)
        }.flatMapLatest { pair ->
            val query = pair.first
            val sort = pair.second

            if (query.isBlank()) {
                repository.getAllNotes(sort)
            } else {
                repository.searchNotes(query)
            }
        }.map { notes ->
            NotesUiState(
                isLoading = false,
                notes = notes,
                searchQuery = _searchQuery.value,
                isEmpty = notes.isEmpty()
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            NotesUiState(isLoading = true)
        )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.insertNote(title, content)
        }
    }

    fun updateNote(id: Long, title: String, content: String) {
        viewModelScope.launch {
            repository.updateNote(id, title, content)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    fun changeTheme(theme: String) {
        viewModelScope.launch {
            settingsManager.setTheme(theme)
        }
    }

    fun changeSortOrder(sortOrder: String) {
        viewModelScope.launch {
            settingsManager.setSortOrder(sortOrder)
        }
    }
}