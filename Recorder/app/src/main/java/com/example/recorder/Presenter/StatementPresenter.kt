package com.example.recorder.Presenter

import com.example.recorder.Model.User
import java.sql.Statement


class StatementPresenter {

    private var user : User
    private lateinit var holder : StatementItemView

    constructor(user:User){
        this.user = user
    }

    fun onBindStatementItemView(holder : StatementItemView, position : Int){
        val statement = user.getRecognizedText()[position]
        this.holder = holder
        holder.setCancellButtonOnClickListener()
        holder.setStatement(statement)
        holder.setOnTextChanged()
        holder.disableButton()
        holder.setPosition(position)
    }

    fun manageTextChange(newText: String, position: Int){
        //holder.disableButton()
        user.changeText(newText,position)
    }

    fun getStatementsCount() : Int{
        return user.getRecognizedText().size
    }

}