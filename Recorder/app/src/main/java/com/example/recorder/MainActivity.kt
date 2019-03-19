package com.example.recorder

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File.separator
import java.util.*
import kotlin.collections.ArrayList

private const val REQ_CODE_SPEECH_INPUT = 100

class MainActivity : AppCompatActivity() {
    private lateinit var mVoiceInputTv : EditText
    private lateinit var mSpeakBtn : ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPermissions()

        mVoiceInputTv = findViewById(R.id.voiceInput)
        mSpeakBtn = findViewById(R.id.btnSpeak)
        mSpeakBtn.setOnClickListener(View.OnClickListener {
            startVoiceInput()
        })
    }

    private fun startVoiceInput(){
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"How can I help you?")
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
                    if(resultCode == Activity.RESULT_OK && data != null) {
                        val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        val text = mVoiceInputTv.getText().toString()
                        mVoiceInputTv.setText(text + " "  + result[0])
                    }

            }
        }
    }


    private fun setupPermissions(){
        val microphonePermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.RECORD_AUDIO)

        val writePermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(microphonePermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE),
            101)
    }
}
