package com.example.recorder.Presenter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import com.example.recorder.Model.User
import java.lang.Exception
import java.util.*

private const val REQ_CODE_SPEECH_INPUT = 100

class SpeechRecognitionActivityPresenter {
    private var view : View
    private var user : User



    constructor(activityView: View){
        view = activityView
        user = User()
    }

    fun updateSilenceLength(length: Int) {
        user.setSilenceLength(length)
    }


    fun updateSilenceLength(length: String) {
        user.setSilenceLength(length)
    }


    fun updateUserEmail(addres:String){
        user.setEmailAddress(addres)
    }

    fun startVoiceInput(){

    }



    fun updateRecognizerResult(result: String){
        //view.addTextToVoiceResult(result)

    }

    interface View{
        fun getViewActivity(): Activity
        fun addTextToVoiceResult(text: String)
        fun startRecognizerActivity()

    }
}