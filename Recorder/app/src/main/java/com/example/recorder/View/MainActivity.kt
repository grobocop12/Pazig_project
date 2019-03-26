package com.example.recorder.View
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.*
import com.example.recorder.Presenter.MainActivityPresenter
import com.example.recorder.R

class MainActivity : AppCompatActivity(), MainActivityPresenter.View {
    private lateinit var etSilenceLenght : EditText
    private lateinit var startActivityButton : Button
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this)
        etSilenceLenght = findViewById(R.id.silenceEditText)
        startActivityButton = findViewById(R.id.startSpeechRecognitionBtn)
        presenter.requestPermissions()

        startActivityButton.setOnClickListener {
            presenter.startRecognitionButtonClicked()
        }

        etSilenceLenght.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.updateSilenceLength(s.toString())
            }
        })
    }

    override fun getViewActivity():Activity{
        return this
    }

    override fun navigateToSpeechRecognitionScreen() {
        presenter.requestPermissions()
        if(!presenter.checkPermission()){
            return
        }
        presenter.startRecognitionActivity()

    }

}
