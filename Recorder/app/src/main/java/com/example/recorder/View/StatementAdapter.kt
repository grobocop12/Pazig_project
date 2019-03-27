package com.example.recorder.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.recorder.Presenter.SpeechRecognitionActivityPresenter
import com.example.recorder.Presenter.StatementItemView
import com.example.recorder.R
import kotlinx.android.synthetic.main.statement_list_item.view.*
import kotlin.contracts.Returns

class StatementAdapter : RecyclerView.Adapter<StatementViewHolder> {

    private var presenter : SpeechRecognitionActivityPresenter

    constructor(presenter: SpeechRecognitionActivityPresenter){
        this.presenter = presenter
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StatementViewHolder {
        return StatementViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.statement_list_item,p0,false))
    }

    override fun getItemCount(): Int {
        return presenter.getStatementsCount()
    }

    override fun onBindViewHolder(p0: StatementViewHolder, p1: Int) {
        presenter.onBindStatementItemView(p0,p1)
    }

}
