package com.example.recorder.Presenter
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import com.example.recorder.Model.User
import com.example.recorder.View.SpeechRecognitionActivity

class MainActivityPresenter {
    private var view: MainView
    private var user: User

    constructor(mainView: MainView) {
        view = mainView
        user = User()
    }

    fun checkPermission(): Boolean {
        val microphonePermission = ContextCompat.checkSelfPermission(
            view.getViewActivity(),
            Manifest.permission.RECORD_AUDIO
        )

        val writePermission = ContextCompat.checkSelfPermission(
            view.getViewActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val internetPermission = ContextCompat.checkSelfPermission(
            view.getViewActivity(),
            Manifest.permission.INTERNET
        )

        if (microphonePermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED || internetPermission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    fun requestPermissions() {
        if(!checkPermission()) {
            ActivityCompat.requestPermissions(
                view.getViewActivity(),
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET
                ),
                101
            )
        }
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

    fun setUpIntent() {
        val activity = view.getViewActivity()
    }

}
