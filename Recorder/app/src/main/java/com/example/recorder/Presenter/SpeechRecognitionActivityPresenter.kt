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
    private var intent : Intent


    constructor(activityView: View){
        view = activityView
        user = User()
        intent = setUpIntent()
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
        println("START")
        view.startRecognizerActivity(intent)
    }

    private fun setUpIntent() : Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Recognizer will close automatically after period of complete silence.")
        return intent
    }

    fun updateRecognizerResult(requestCode: Int, resultCode:Int, data:Intent?){
        println(requestCode)
        println(resultCode)
        println(data)

    }

    interface View{
        fun getViewActivity(): Activity
        fun addTextToVoiceResultEditText(text: String)
        fun startRecognizerActivity(intent: Intent)
    }
}