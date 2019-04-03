package com.example.recorder.Model

import java.lang.NumberFormatException

class User {
    private var emailAddress: String? = null
    private var rawRecognizedText: ArrayList<String> = ArrayList()
    private var modifiedRecognizedText: ArrayList<String> = ArrayList()
    private var silenceLength: Int? = null

    fun setEmailAddress(address: String) {
        emailAddress = address
    }

    fun getEmailAddress(): String? {
        return emailAddress
    }

    fun changeText(newText: String, index : Int){
        modifiedRecognizedText[index] = newText
    }

    fun appendRecognizedText(text: String) {
        rawRecognizedText.add(text)
        modifiedRecognizedText.add(rawRecognizedText.last())
    }

    fun getModifiedRecognizedText(): ArrayList<String> {
        return modifiedRecognizedText
    }

    fun getRawText(index :Int):String{
        return rawRecognizedText[index]
    }

    fun setSilenceLength(length: String) {
        try {
            var value = length.toInt()
            if (value >= 0) {
                silenceLength = value
            }
        } catch (exc: NumberFormatException) {

        }
    }

    fun setSilenceLength(length: Int) {
        if (length >= 0) {
            silenceLength = length
        }
    }

    fun getSilenceLength(): Int? {
        return silenceLength
    }

}