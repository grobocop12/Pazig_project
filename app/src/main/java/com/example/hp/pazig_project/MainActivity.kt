package com.example.hp.pazig_project

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import java.util.ArrayList
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private var mVoiceInputTv: TextView? = null
    private var mSpeakBtn: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mVoiceInputTv = findViewById<View>(R.id.voiceInput) as TextView
        mSpeakBtn = findViewById<View>(R.id.btnSpeak) as ImageButton
        mSpeakBtn!!.setOnClickListener { startVoiceInput() }
    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?")
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_CODE_SPEECH_INPUT -> {
                if (resultCode == RESULT_OK && null != data) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    mVoiceInputTv!!.text = result[0]
                }
            }
        }
    }

    companion object {

        private val REQ_CODE_SPEECH_INPUT = 100
    }
}

