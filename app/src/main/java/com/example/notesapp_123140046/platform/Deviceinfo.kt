package com.example.notesapp_123140046.platform

import android.os.Build

class DeviceInfo {

    fun getDeviceName(): String {
        return "${Build.MANUFACTURER} ${Build.MODEL}"
    }

    fun getOsVersion(): String {
        return "Android ${Build.VERSION.RELEASE} API ${Build.VERSION.SDK_INT}"
    }

    fun getAppVersion(): String {
        return "1.0"
    }
}