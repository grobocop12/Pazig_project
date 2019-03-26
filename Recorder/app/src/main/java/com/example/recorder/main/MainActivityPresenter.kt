package com.example.recorder.main

import android.view.View

public class MainActivityPresenter{
    var view:View

    constructor(mainView:View){
        view = mainView
    }

    interface View{

    }
}