package com.example.recorder.view

import android.app.Activity
import android.content.*
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.example.recorder.R
import com.example.recorder.presenter.SpeechRecognitionPresenter
import java.util.*

private const val REQ_CODE_SPEECH_INPUT = 100

class SpeechRecognitionActivity : AppCompatActivity(), RecognizerView {

    private lateinit var rvStatementsList: RecyclerView
    private lateinit var presenter: SpeechRecognitionPresenter
    private lateinit var container: LinearLayout
    private lateinit var btnCopyToClipboard: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speech_recognition)

        val bundleExtra = intent.getBundleExtra(USER_BUNDLE_STRING_EXTRA)

        presenter = SpeechRecognitionPresenter(this, bundleExtra)
        container = findViewById(R.id.btnSpeakContainer)
        rvStatementsList = findViewById(R.id.rvStatements_list)
        btnCopyToClipboard = findViewById(R.id.btnCopy)

        rvStatementsList.layoutManager = LinearLayoutManager(this)
        rvStatementsList.adapter = StatementAdapter(presenter.createStatementPresenter())

        container.setOnClickListener {
            startRecognizerActivity()
        }
        btnCopyToClipboard.setOnClickListener {
            presenter.copyToClipboard()
        }
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
            }
        }
    }

    override fun updateRecyclerView() {
        rvStatementsList.adapter?.notifyDataSetChanged()
    }

    override fun putTextOnClipboard(text: String) {
        val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(getString(R.string.clipboardLabel), text)
        clipboard.primaryClip = clip
        Toast.makeText(
            this, getString(R.string.toastMessage),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun startRecognizerActivity() {
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
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true)
        intent.putExtra(
            RecognizerIntent.EXTRA_PROMPT,
            "Recognizer will close automatically after "
                    + presenter.getSilenceLength().toString()
                    + " milliseconds of complete silence."
        )
        intent.putExtra(
            RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS,
            presenter.getSilenceLength()
        )
        return intent
    }

}
