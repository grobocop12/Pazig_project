package com.example.recorder.Presenter

import android.app.Activity

interface RecognizerView{
    fun getViewActivity(): Activity
    fun updateRecyclerView()
    fun putTextOnClipboard(text:String)
}