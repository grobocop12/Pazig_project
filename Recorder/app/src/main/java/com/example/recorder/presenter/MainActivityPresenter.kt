package com.example.recorder.presenter
import android.os.Bundle
import com.example.recorder.model.User
import com.example.recorder.view.MainView

const val emailKey = "user email"
const val silenceLengthKey = "silence length"


class MainActivityPresenter {
    private var view: MainView
    private var user: User

    constructor(mainView: MainView) {
        view = mainView
        user = User()
    }

    fun startRecognitionButtonClicked() {
        view.navigateToSpeechRecognitionScreen()
    }

    fun updateSilenceLength(length: String) {
        user.setSilenceLength(length)
    }

    fun updateSilenceLength(length: Int) {
        user.setSilenceLength(length)
    }

    fun updateUserEmail(addres:String){
        user.setEmailAddress(addres)
    }

    fun setUpUserBundle() : Bundle{
        val userBundle = Bundle()
        userBundle.putString(emailKey,user.getEmailAddress())
        if(user.getSilenceLength()!=null) {
            userBundle.putInt(silenceLengthKey, user.getSilenceLength()!!)
        }

        return userBundle
    }

}
