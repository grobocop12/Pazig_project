package com.example.recorder.Presenter

interface StatementItemView {
    fun setStatement(text : String)
    fun setOnTextChanged()
    fun setCancellButtonOnClickListener()
    fun disableButton()
    fun setPosition(newPosition:Int)
}