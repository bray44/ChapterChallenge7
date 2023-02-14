package com.example.chapterchallenge7.gameplay.data

import android.content.Context


//Gameplay Data dibuat dengan menggunakan SharedPreferences
class GameplayData(name: String, context: Context) {

    private val sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    // Method untuk mengambil data nama player dan itemghdgdfgdssd
    fun getString(key: String, defaultValue: String): String? {
        return sharedPref.getString(key, defaultValue)
    }

    // Method akan digunakan untuk menyimpan nama player dan item
    fun saveString(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    // Method untuk mengambil data skor
    fun getInt(key: String, defaultValue: Int): Int? {
        return sharedPref.getInt(key, defaultValue)
    }

    // Method digunakan untuk menyimpan skor dan mereset skor
    fun saveInt(key: String, value: Int) {
        sharedPref.edit().putInt(key, value).apply()
    }




}