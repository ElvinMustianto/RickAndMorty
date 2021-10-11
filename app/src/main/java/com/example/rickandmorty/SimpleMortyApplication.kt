package com.example.rickandmorty

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SimpleMortyApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}