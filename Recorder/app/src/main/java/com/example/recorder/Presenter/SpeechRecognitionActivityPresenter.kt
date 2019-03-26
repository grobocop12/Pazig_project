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
        if(view.getViewActivity().intent.getStringExtra("userEmail")!= null) {
            user.setEmailAddress(view.getViewActivity().intent.getStringExtra("userEmail"))
        }
        if(view.getViewActivity().intent.getStringExtra("userSilenceLength") != null) {
            user.setSilenceLength(view.getViewActivity().intent.getStringExtra("userSilenceLength"))
        }
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

    fun updateUI(){
        if(user.getRecognizedText()!= null) {
            view.updateVoiceInputEditText(user.getRecognizedText() as String)
        }
    }


    fun updateRecognizerResult(result: String){
        if(user.getRecognizedText() != null) {
            user.setRecognizedText(user.getRecognizedText() + " "+ result)
        }else{
            user.setRecognizedText(result)
        }
        view.updateVoiceInputEditText(user.getRecognizedText() as String)
    }

    interface View{
        fun getViewActivity(): Activity
        fun updateVoiceInputEditText(text: String)
        fun startRecognizerActivity()
    }
}