package com.example.notesapp_123140046.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsDialog(
    currentTheme: String,
    currentSortOrder: String,
    onDismiss: () -> Unit,
    onThemeChange: (String) -> Unit,
    onSortOrderChange: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Settings")
        },
        text = {
            Column {
                Text("Theme")

                RadioOption(
                    label = "System",
                    selected = currentTheme == "system",
                    onClick = { onThemeChange("system") }
                )

                RadioOption(
                    label = "Light",
                    selected = currentTheme == "light",
                    onClick = { onThemeChange("light") }
                )

                RadioOption(
                    label = "Dark",
                    selected = currentTheme == "dark",
                    onClick = { onThemeChange("dark") }
                )

                Text("Sort Order")

                RadioOption(
                    label = "Newest",
                    selected = currentSortOrder == "newest",
                    onClick = { onSortOrderChange("newest") }
                )

                RadioOption(
                    label = "Oldest",
                    selected = currentSortOrder == "oldest",
                    onClick = { onSortOrderChange("oldest") }
                )
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Tutup")
            }
        }
    )
}

@Composable
fun RadioOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    ListItem(
        headlineContent = { Text(label) },
        leadingContent = {
            RadioButton(
                selected = selected,
                onClick = onClick
            )
        }
    )
}