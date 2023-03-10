package com.example.chapterchallenge7.splashscreen.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.chapterchallenge7.splashscreen.data.FirstInstall

class FirstInstallViewModel(application: Application): AndroidViewModel(application) {

    fun createFirstInstallData(name: String): FirstInstall {
        return FirstInstall(name, getApplication())
    }

    fun getBoolean(data: FirstInstall, defaultValue: Boolean): Boolean {
        return data.getBoolean("STATE", defaultValue)
    }
    // Method akan digunakan untuk menyimpan nama player dan item
    fun saveBoolean(data: FirstInstall, value: Boolean) {
        data.putBoolean("STATE", value)
    }

}