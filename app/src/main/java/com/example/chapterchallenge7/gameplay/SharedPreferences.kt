package com.example.chapterchallenge7.gameplay

import android.content.Context

class SharedPreferences(name: String, context: Context) {

    private val sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    // Method akan digunakan untuk menyimpan nama player dan item
    fun saveString(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    // Method digunakan untuk menyimpan skor dan mereset skor
    fun saveInt(key: String, value: Int) {
        sharedPref.edit().putInt(key, value).apply()
    }

    // Method untuk mengambil data nama player dan item
    fun getString(key: String, defaultValue: String): String? {
        return sharedPref.getString(key, defaultValue)
    }

    // Method untuk mengambil data skor
    fun getInt(key: String, defaultValue: Int): Int? {
        return sharedPref.getInt(key, defaultValue)
    }

}