package com.example.recorder.Model

import java.lang.NumberFormatException

class User {
    private var emailAddress: String? = null
    private var recognizedText: String? = null
    private var silenceLength: Int? = null

    fun setEmailAddress(address: String) {
        emailAddress = address
    }

    fun getEmailAddress(): String? {
        return emailAddress
    }

    fun setRecognizedText(text: String) {
        recognizedText = text
    }

    fun getRecognizedText(): String? {
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