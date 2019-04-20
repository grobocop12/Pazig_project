package com.example.recorder.view

interface StatementItemView {
    fun setStatement(text : String?)
    fun setOnTextChanged()
    fun setCancelButtonOnClickListener()
    fun enableButton()
    fun setUnchangedText(text: String)
    fun setIndex(newIndex : Int)
    fun deleteStatement()
}