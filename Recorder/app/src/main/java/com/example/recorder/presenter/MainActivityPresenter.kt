package com.example.recorder.presenter
import android.Manifest
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.recorder.model.User
import com.example.recorder.view.MainView

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

}
