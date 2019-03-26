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
    private var view: View
    private var user: User

    constructor(mainView: View) {
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

        if (microphonePermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

    fun requestPermissions() {
        if(checkPermission()) {
            ActivityCompat.requestPermissions(
                view.getViewActivity(),
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
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

    fun startRecognitionActivity(){
        val activity = view.getViewActivity()
        val intent = Intent(activity,SpeechRecognitionActivity::class.java)

        intent.putExtra("userEmail", user.getEmailAddress())
        intent.putExtra("userSilenceLength", user.getSilenceLength().toString())

        activity.startActivity(intent)
    }

    interface View {
        fun getViewActivity(): Activity
        fun navigateToSpeechRecognitionScreen()
    }
}