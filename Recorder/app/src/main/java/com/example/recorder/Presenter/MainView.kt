package com.example.recorder.Presenter

import android.app.Activity


interface MainView {
    fun getViewActivity(): Activity
    fun navigateToSpeechRecognitionScreen()
}