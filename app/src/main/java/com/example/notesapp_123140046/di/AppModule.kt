package com.example.notesapp_123140046.di

import com.example.notesapp_123140046.platform.DeviceInfo
import com.example.notesapp_123140046.platform.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { DeviceInfo() }
    single { NetworkMonitor(androidContext()) }

    // Nanti repository/viewmodel kamu juga bisa dimasukkan di sini
    // single { NoteRepository(get()) }
    // viewModel { NoteViewModel(get()) }
}