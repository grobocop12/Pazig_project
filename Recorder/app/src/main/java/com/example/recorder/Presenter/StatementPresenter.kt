package com.example.recorder.Presenter

import com.example.recorder.Model.User


class StatementPresenter {

    private var user : User
    private lateinit var holder : StatementItemView

    constructor(user:User){
        this.user = user
    }

    fun onBindStatementItemView(holder : StatementItemView, position : Int){
        val statement = user.getModifiedRecognizedText()[position]
        this.holder = holder
        holder.setIndex(position)
        holder.setCancellButtonOnClickListener()
        holder.setUnchangedText(user.getRawText(position))
        holder.setStatement(statement)
        holder.enableButton()
        holder.setOnTextChanged()
    }

    fun manageTextChange(newText: String, position: Int){
        user.changeText(newText,position)
    }

    fun getStatementsCount() : Int{
        return user.getModifiedRecognizedText().size
    }

}