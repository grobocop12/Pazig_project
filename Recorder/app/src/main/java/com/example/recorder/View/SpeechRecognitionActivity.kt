package com.example.recorder.View

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.*
import com.example.recorder.Presenter.RecognizerView
import com.example.recorder.Presenter.SpeechRecognitionActivityPresenter
import com.example.recorder.R
import java.util.*

private const val REQ_CODE_SPEECH_INPUT = 100

class SpeechRecognitionActivity : AppCompatActivity(), RecognizerView {

    private lateinit var etResultText: EditText
    private lateinit var mSpeakBtn: ImageButton
    private lateinit var presenter: SpeechRecognitionActivityPresenter
    private lateinit var container: LinearLayout
    private lateinit var textContainer : LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_recognition)

        presenter = SpeechRecognitionActivityPresenter(this)
        //etResultText = findViewById(R.id.voiceInput)
        mSpeakBtn = findViewById(R.id.btnSpeak)
        container = findViewById(R.id.btnSpeakContainer)
        textContainer = findViewById(R.id.textContainer)
        container.setOnClickListener(View.OnClickListener {
            startRecognizerActivity()
        })
        Log.i("","View Created")
    }


    override fun getViewActivity(): Activity {
        return this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if ((resultCode == Activity.RESULT_OK) && data != null) {
                var result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                presenter.updateRecognizerResult(result[0])
                val edit = EditText(this)
                edit.setText(result[0])
                textContainer.addView(edit)
            }
        }


    }


    override fun updateVoiceInputEditText(text: String) {
        etResultText.setText(text)

    }

    override fun startRecognizerActivity() {
        val intent = setUpIntent()
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT)
        } catch (a: ActivityNotFoundException) {

        }

    }

    override fun setOnClick() {
        container.setOnClickListener(View.OnClickListener {
            startRecognizerActivity()
        })
    }

    private fun setUpIntent(): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Recognizer will close automatically after period of complete silence."
        )
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 20)
        return intent
    }


}
