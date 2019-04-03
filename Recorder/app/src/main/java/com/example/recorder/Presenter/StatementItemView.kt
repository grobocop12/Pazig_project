package com.example.recorder.Presenter

interface StatementItemView {
    fun setStatement(text : String?)
    fun setOnTextChanged()
    fun setCancellButtonOnClickListener()
    fun enableButton()
    fun setUnchangedText(text: String)
    fun setIndex(newIndex : Int)
}