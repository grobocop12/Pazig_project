package com.example.recorder.Presenter

import com.example.recorder.Model.User
import java.sql.Statement


class StatementPresenter {

    private var user : User
    private lateinit var holder : StatementItemView
    private var position = -1

    constructor(user:User){
        this.user = user
    }

    fun onBindStatementItemView(holder : StatementItemView, position : Int){
        val statement = user.getRecognizedText()[position]
        this.holder = holder
        this.position = position
        holder.setCancellButtonOnClickListener()
        holder.setStatement(statement)
        holder.setOnTextChanged()
        holder.disableButton()
    }

    fun manageTextChange(newText: String){
        holder.disableButton()

    }

    fun getStatementsCount() : Int{
        return user.getRecognizedText().size
    }

}