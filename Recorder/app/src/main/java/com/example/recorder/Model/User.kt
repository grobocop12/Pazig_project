package com.example.recorder.Model

import java.lang.NumberFormatException

class User {
    private lateinit var emailAddress: String
    private lateinit var recognizedText: String
    private var silenceLength: Int? = null

    fun setEmailAddress(address: String) {
        emailAddress = address
    }

    fun getEmailAddress(): String {
        return emailAddress
    }

    fun setRecognizedText(text: String) {
        recognizedText = text
    }

    fun getRecognizedText(): String {
        return recognizedText
    }

    fun setSilenceLength(length: String) {
        try {
            silenceLength = length.toInt()
        } catch (exc: NumberFormatException) {

        }
    }

    fun getSilenceLength(): Int? {
        return silenceLength
    }

}