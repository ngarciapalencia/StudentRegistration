package com.example.contactmvvm

import android.app.Application
import androidx.room.Room

class MainApplication : Application() {

    companion object {
        lateinit var studentDatabase: StudentDatabase
    }

    override fun onCreate() {
        super.onCreate()

        studentDatabase = Room.databaseBuilder(
            applicationContext,
            StudentDatabase::class.java,
            "students.db"
        ).build()
    }
}