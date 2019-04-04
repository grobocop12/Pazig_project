package com.example.recorder.Presenter

interface StatementItemView {
    fun setStatement(text : String?)
    fun setOnTextChanged()
    fun setCancelButtonOnClickListener()
    fun enableButton()
    fun setUnchangedText(text: String)
    fun setIndex(newIndex : Int)
    fun deleteStatement()
}