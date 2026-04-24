package com.example.notesapp_123140046.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp_123140046.model.NoteModel
import com.example.notesapp_123140046.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val theme by viewModel.theme.collectAsState()
    val sortOrder by viewModel.sortOrder.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var editingNote by remember { mutableStateOf<NoteModel?>(null) }
    var showSettings by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NotesApp_123140046") },
                actions = {
                    TextButton(onClick = { showSettings = true }) {
                        Text("Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = viewModel::onSearchQueryChange,
                label = { Text("Cari notes") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }

                uiState.isEmpty -> {
                    Text("Belum ada catatan.")
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.notes) { note ->
                            NoteItem(
                                note = note,
                                onEdit = { editingNote = note },
                                onDelete = { viewModel.deleteNote(note.id) }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        NoteFormDialog(
            titleDialog = "Tambah Note",
            initialTitle = "",
            initialContent = "",
            onDismiss = { showAddDialog = false },
            onSave = { title, content ->
                viewModel.addNote(title, content)
                showAddDialog = false
            }
        )
    }

    editingNote?.let { note ->
        NoteFormDialog(
            titleDialog = "Edit Note",
            initialTitle = note.title,
            initialContent = note.content,
            onDismiss = { editingNote = null },
            onSave = { title, content ->
                viewModel.updateNote(note.id, title, content)
                editingNote = null
            }
        )
    }

    if (showSettings) {
        SettingsDialog(
            currentTheme = theme,
            currentSortOrder = sortOrder,
            onDismiss = { showSettings = false },
            onThemeChange = viewModel::changeTheme,
            onSortOrderChange = viewModel::changeSortOrder
        )
    }
}

@Composable
fun NoteItem(
    note: NoteModel,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                TextButton(onClick = onEdit) {
                    Text("Edit")
                }

                TextButton(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}