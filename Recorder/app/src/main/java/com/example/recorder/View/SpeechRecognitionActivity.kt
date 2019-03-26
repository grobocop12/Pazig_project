package com.example.recorder.View

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.example.recorder.Presenter.SpeechRecognitionActivityPresenter
import com.example.recorder.R
import java.util.*

private const val REQ_CODE_SPEECH_INPUT = 100

class SpeechRecognitionActivity : AppCompatActivity(), SpeechRecognitionActivityPresenter.View {

    private lateinit var etResultText: TextView
    private lateinit var mSpeakBtn: ImageButton
    private lateinit var presenter: SpeechRecognitionActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_recognition)

        presenter = SpeechRecognitionActivityPresenter(this)
        etResultText = findViewById(R.id.voiceInput)
        mSpeakBtn = findViewById(R.id.btnSpeak)

        mSpeakBtn.setOnClickListener(View.OnClickListener {
            startRecognizerActivity()
        })
    }


    override fun getViewActivity(): Activity {
        return this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if ((resultCode == Activity.RESULT_OK) && data != null) {
                var result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                presenter.updateRecognizerResult(result[0])
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

    private fun setUpIntent(): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Recognizer will close automatically after period of complete silence."
        )
        intent.putExtra(RecognizerIntent.EXTRA_RESULTS_PENDINGINTENT, true)
        intent.putExtra(RecognizerIntent.ACTION_VOICE_SEARCH_HANDS_FREE, true)
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 20)
        return intent
    }


}
