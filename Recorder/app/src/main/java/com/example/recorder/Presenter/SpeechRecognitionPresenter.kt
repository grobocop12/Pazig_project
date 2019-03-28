package com.example.recorder.Presenter

import com.example.recorder.Model.User


class SpeechRecognitionPresenter {
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

    fun createStatementPresenter(): StatementPresenter{
        val presenter = StatementPresenter(user)
        return presenter
    }

    fun updateRecognizerResult(result: String){
        user.appendRecognizedText(result)
        view.updateRecyclerView()
    }




}
