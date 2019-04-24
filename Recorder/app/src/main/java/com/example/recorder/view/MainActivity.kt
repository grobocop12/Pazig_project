package com.example.recorder.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import com.example.recorder.R
import com.example.recorder.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var etSilenceLenght: EditText
    private lateinit var startActivityButton: Button
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this)
        etSilenceLenght = findViewById(R.id.silenceEditText)
        startActivityButton = findViewById(R.id.startSpeechRecognitionBtn)


        startActivityButton.setOnClickListener {
            presenter.startRecognitionButtonClicked()
        }

        etSilenceLenght.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updateSilenceLength(s.toString())
            }
        })

        requestPermissions()
    }

    override fun getViewActivity(): Activity {
        return this
    }

    override fun navigateToSpeechRecognitionScreen() {
        requestPermissions()
        if (!checkPermission()) {
            return
        }
        val intent = Intent(this, SpeechRecognitionActivity::class.java)
        startActivity(intent)

    }

    private fun requestPermissions() {
        if (!checkPermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET
                ),
                101
            )
        }
    }

    private fun checkPermission(): Boolean {
        val microphonePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        )

        val writePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val internetPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        )

        if (microphonePermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED || internetPermission != PackageManager.PERMISSION_GRANTED) {
            return false
        }
        return true
    }

}
