package com.example.notesapp_123140046

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp_123140046.data.local.DatabaseProvider
import com.example.notesapp_123140046.data.settings.SettingsManager
import com.example.notesapp_123140046.repository.NoteRepository
import com.example.notesapp_123140046.ui.screens.NotesScreen
import com.example.notesapp_123140046.ui.theme.NotesAppTheme
import com.example.notesapp_123140046.viewmodel.NotesViewModel
import com.example.notesapp_123140046.viewmodel.NotesViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = NoteRepository(database)
        val settingsManager = SettingsManager(applicationContext)

        setContent {
            val notesViewModel: NotesViewModel = viewModel(
                factory = NotesViewModelFactory(repository, settingsManager)
            )

            val theme by notesViewModel.theme.collectAsState()

            NotesAppTheme(theme = theme) {
                NotesScreen(viewModel = notesViewModel)
            }
        }
    }
}