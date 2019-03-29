package com.example.hp.pazig_project

import android.net.Uri;
import android.text.SpannableStringBuilder
import android.app.Activity;

import android.widget.Button;
import android.widget.EditText;
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var mVoiceInputTv: TextView? = null
    private var mSpeakBtn: ImageButton? = null
    private var sendBtn: Button? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mVoiceInputTv = findViewById<View>(R.id.voiceInput) as TextView
        mSpeakBtn = findViewById<View>(R.id.btnSpeak) as ImageButton
        mSpeakBtn!!.setOnClickListener { startVoiceInput() }

        sendBtn = findViewById(R.id.Send) as Button;
        sendBtn!!.setOnClickListener{ sendEmail()}

    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Witaj Powiedz coś ziomuś")
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
                    wypowiedz.text=  SpannableStringBuilder(result[0])
                }
            }
        }
    }

    companion object {

        private val REQ_CODE_SPEECH_INPUT = 100
    }

    protected fun sendEmail() {

        val recipients = arrayOf<String>(mail.getText().toString())
        val email = Intent(Intent.ACTION_SEND, Uri.parse("mailto:"))
        // prompts email clients only
        email.type = "message/rfc822"

        email.putExtra(Intent.EXTRA_EMAIL, recipients)
        email.putExtra(Intent.EXTRA_SUBJECT, "Notatka "+ Calendar.getInstance().time)
        email.putExtra(Intent.EXTRA_TEXT, wypowiedz.getText().toString())

        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email, "Choose an email client from..."))

        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(
                this@MainActivity, "No email client installed.",
                Toast.LENGTH_LONG
            ).show()
        }

    }

}

