package com.example.recorder.Presenter
import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.recorder.Model.User

class MainActivityPresenter{
    private var view: View
    private var user: User

    constructor(mainView: View){
        view = mainView
        user = User()
    }

    fun requestPermissions(){
        val microphonePermission = ContextCompat.checkSelfPermission(
            view.getViewActivity(),
            Manifest.permission.RECORD_AUDIO)

        val writePermission = ContextCompat.checkSelfPermission(
            view.getViewActivity(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(microphonePermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                view.getViewActivity(),
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE),
                101)
        }
    }

    fun startRecognitionButtonClicked(){
        view.navigateToSpeechRecognitionScreen()
    }

    fun updateSilenceLength(length : String){
        user.setSilenceLength(length)
    }

    interface View{
        fun getViewActivity() : Activity
        fun navigateToSpeechRecognitionScreen()
    }
}