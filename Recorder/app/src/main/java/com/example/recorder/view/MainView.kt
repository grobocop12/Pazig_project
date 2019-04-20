package com.example.recorder.view

import android.app.Activity


interface MainView {
    fun getViewActivity(): Activity
    fun navigateToSpeechRecognitionScreen()
}