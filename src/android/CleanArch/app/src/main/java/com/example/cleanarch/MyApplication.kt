package com.example.cleanarch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // тут Hilt типо генерит контейнер с классами, который мы хотим шарить в разных частях
}