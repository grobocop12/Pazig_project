package com.example.recorder

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import java.util.*

private const val REQ_CODE_SPEECH_INPUT = 100

class SpeechRecognitionActivity : AppCompatActivity() {

    private lateinit var mVoiceInputTv : EditText
    private lateinit var mSpeakBtn : ImageButton
    private lateinit var speechRecognitionIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_recognition)

        mVoiceInputTv = findViewById(R.id.voiceInput)
        mSpeakBtn = findViewById(R.id.btnSpeak)
        mSpeakBtn.setOnClickListener(View.OnClickListener {
            startVoiceInput()
        })
    }

    private fun startVoiceInput(){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Recognizer will close automatically after 25 milliseconds of complete silence.")
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,0)
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true)
        intent.putExtra(RecognizerIntent.EXTRA_RESULTS_PENDINGINTENT_BUNDLE,true)
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        }catch(a : ActivityNotFoundException)
        {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode:Int, data:Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQ_CODE_SPEECH_INPUT ->{
                if((resultCode == Activity.RESULT_OK || resultCode == 0) && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    val text = mVoiceInputTv.getText().toString()
                    mVoiceInputTv.setText(text + " "  + result[0])
                }
            }
        }
    }

}
