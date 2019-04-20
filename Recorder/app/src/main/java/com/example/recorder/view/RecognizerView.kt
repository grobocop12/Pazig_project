package com.example.recorder.view

import android.app.Activity

interface RecognizerView{
    fun getViewActivity(): Activity
    fun updateRecyclerView()
    fun putTextOnClipboard(text:String)
}