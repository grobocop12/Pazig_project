package com.example.recorder.Presenter

import com.example.recorder.Model.User


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

    fun updateRecognizerResult(result: String){
        user.appendRecognizedText(result)
        view.updateRecyclerView()
    }

    fun onBindStatementItemView(holder : StatementItemView, position : Int){
        val statement = user.getRecognizedText()[position]
        holder.setStatement(statement)
    }

    fun getStatementsCount() : Int{
        return user.getRecognizedText().size
    }


}
