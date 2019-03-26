package com.example.recorder.View

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.recorder.Presenter.SpeechRecognitionActivityPresenter
import com.example.recorder.R
import java.lang.Exception
import java.lang.IllegalStateException

private const val REQ_CODE_SPEECH_INPUT = 100

class SpeechRecognitionActivity : AppCompatActivity(), SpeechRecognitionActivityPresenter.View {

    private lateinit var etResultText: EditText
    private lateinit var mSpeakBtn: ImageButton
    private lateinit var presenter: SpeechRecognitionActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_recognition)

        presenter = SpeechRecognitionActivityPresenter(this)
        val email = this.intent.getStringExtra("userEmail")
        if (email != null) {
            presenter.updateUserEmail(email)
        }
        val silenceLength = this.intent.getStringExtra("userSilenceLength")
        if (silenceLength != null) {
            presenter.updateSilenceLength(silenceLength)
        }

        etResultText = findViewById(R.id.voiceInput)
        mSpeakBtn = findViewById(R.id.btnSpeak)

        mSpeakBtn.setOnClickListener {
            presenter.startVoiceInput()
        }
    }

    override fun getViewActivity(): Activity {
        return this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("onActivityResult")
        super.onActivityResult(requestCode, resultCode, data)
        println("super onActivityResult")
        when(requestCode){
            REQ_CODE_SPEECH_INPUT ->{
                if((resultCode == Activity.RESULT_OK) && data != null) {
                    presenter.updateRecognizerResult(requestCode, resultCode, data)
                }
            }
        }


    }

    override fun addTextToVoiceResultEditText(text: String) {
        etResultText.append(" " + text)
    }

    override fun startRecognizerActivity(intent: Intent){
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        }catch(a : ActivityNotFoundException)
        {

        }
    }

}
