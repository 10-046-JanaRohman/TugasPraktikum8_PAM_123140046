package com.example.notesapp_123140046.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notesapp_123140046.platform.DeviceInfo
import org.koin.compose.koinInject

@Composable
fun SettingsDialog(
    currentTheme: String,
    currentSortOrder: String,
    onDismiss: () -> Unit,
    onThemeChange: (String) -> Unit,
    onSortOrderChange: (String) -> Unit
) {
    val deviceInfo: DeviceInfo = koinInject()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Settings")
        },
        text = {
            Column {
                Text("Device Info")
                Text("Device: ${deviceInfo.getDeviceName()}")
                Text("OS: ${deviceInfo.getOsVersion()}")
                Text("App Version: ${deviceInfo.getAppVersion()}")

                Spacer(modifier = Modifier.height(16.dp))

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

                Spacer(modifier = Modifier.height(16.dp))

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