package com.example.recorder.Model

import java.lang.NumberFormatException

class User {
    private var emailAddress: String? = null
    private var recognizedText: ArrayList<String> = ArrayList()
    private var silenceLength: Int? = null

    fun setEmailAddress(address: String) {
        emailAddress = address
    }

    fun getEmailAddress(): String? {
        return emailAddress
    }

    fun changeText(newText: String, index : Int){
        recognizedText[index] = newText
    }

    fun appendRecognizedText(text: String) {
        recognizedText.add(text)
    }

    fun getRecognizedText(): ArrayList<String> {
        return recognizedText
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