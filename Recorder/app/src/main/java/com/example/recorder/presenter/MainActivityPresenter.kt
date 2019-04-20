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

}
