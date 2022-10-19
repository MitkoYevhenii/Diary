package com.example.diary

import android.app.Application
import com.example.diary.model.UserService

class App : Application() {
    val userService = UserService()
}