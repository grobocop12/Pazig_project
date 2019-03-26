package com.example.recorder.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.widget.*
import com.example.recorder.R
import com.example.recorder.SpeechRecognitionActivity

class MainActivity : AppCompatActivity(), MainActivityPresenter.View{
    private lateinit var etSilenceLenght : EditText
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this)

        etSilenceLenght = findViewById<EditText>(R.id.silenceEditText)

        setupPermissions()

        val startActivityButton = findViewById<Button>(R.id.startSpeechRecognitionBtn)
        startActivityButton.setOnClickListener {
            val intent = Intent(this, SpeechRecognitionActivity::class.java)
            if(!TextUtils.isEmpty(etSilenceLenght.text)){
                intent.putExtra("silenceLength", etSilenceLenght.text.toString().toInt())
            }
            startActivity(intent)
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
