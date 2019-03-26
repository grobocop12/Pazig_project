package com.example.recorder.Presenter

import com.example.recorder.Model.User
import com.example.recorder.View.SpeechRecognitionActivity

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

    interface View{

    }
}