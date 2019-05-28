package com.example.recorder.presenter

import android.os.Bundle
import com.example.recorder.model.User
import com.example.recorder.view.RecognizerView


class SpeechRecognitionPresenter {
    private var view : RecognizerView
    private var user : User


    constructor(activityView: RecognizerView, userInfoBundle : Bundle?){
        view = activityView
        user = User()

        if(userInfoBundle!=null) {
            setUpUser(userInfoBundle)
        }

        if(view.getViewActivity().intent.getStringExtra("userEmail")!= null) {
            user.setEmailAddress(view.getViewActivity().intent.getStringExtra("userEmail"))
        }
        if(view.getViewActivity().intent.getStringExtra("userSilenceLength") != null) {
            user.setSilenceLength(view.getViewActivity().intent.getStringExtra("userSilenceLength"))
        }
    }

    private fun setUpUser(userInfoBundle: Bundle) {
        val email = userInfoBundle.getString(emailKey)
        if(email!=null) {
            user.setEmailAddress(email)
        }
        user.setSilenceLength(userInfoBundle.getInt(silenceLengthKey))
    }

    fun createStatementPresenter(): StatementPresenter{
         return StatementPresenter(user)
    }

    fun updateRecognizerResult(result: String){
        user.appendRecognizedText(result)
        view.updateRecyclerView()
    }

    fun copyToClipboard(){
        val text = user.getModifiedRecognizedText().joinToString()
        view.putTextOnClipboard(text)
    }

    fun getSilenceLength() : Int?{
        return user.getSilenceLength()
    }

}
