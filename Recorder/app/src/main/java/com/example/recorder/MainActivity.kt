package com.example.recorder

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log

class MainActivity : AppCompatActivity() {

    lateinit var recorder : MediaRecorder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupPermissions()
        recorder = MediaRecorder()
        Log.i("recorder",recorder.activeMicrophones.toString())
    }

    private fun setupPermissions(){
        val microphonePermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.RECORD_AUDIO)


        val writePermission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(microphonePermission != PackageManager.PERMISSION_GRANTED || writePermission != PackageManager.PERMISSION_GRANTED){
            //Log.i("Permission","Permission to record denied")
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.RECORD_AUDIO,Manifest.permission.WRITE_EXTERNAL_STORAGE),
            101)
    }
}
