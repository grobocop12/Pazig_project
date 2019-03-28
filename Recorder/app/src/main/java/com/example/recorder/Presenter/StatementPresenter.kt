package com.example.recorder.Presenter

import com.example.recorder.Model.User


class StatementPresenter {

    private var user : User
    //private var view : StatementItemView

    constructor(user:User){
        this.user = user
    }

    fun onBindStatementItemView(holder : StatementItemView, position : Int){
        val statement = user.getRecognizedText()[position]
        holder.setStatement(statement)
    }

    fun getStatementsCount() : Int{
        return user.getRecognizedText().size
    }

}