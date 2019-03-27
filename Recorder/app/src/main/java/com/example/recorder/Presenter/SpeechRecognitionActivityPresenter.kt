package com.example.recorder.Presenter

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.speech.RecognizerIntent
import android.view.View
import android.widget.Toast
import com.example.recorder.Model.User
import java.lang.Exception
import java.util.*

private const val REQ_CODE_SPEECH_INPUT = 100

class SpeechRecognitionActivityPresenter {
    private var view : RecognizerView
    private var user : User



    constructor(activityView: RecognizerView){
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

    fun attachOnClickListener(){
        view.setOnClick()
    }

    fun updateRecognizerResult(result: String){
        user.appendRecognizedText(result)
        //view.updateVoiceInputEditText(user.getRecognizedText())
    }


}
public interface RecognizerView{
    fun setOnClick()
    fun getViewActivity(): Activity
    fun updateVoiceInputEditText(text: String)
    fun startRecognizerActivity()
}