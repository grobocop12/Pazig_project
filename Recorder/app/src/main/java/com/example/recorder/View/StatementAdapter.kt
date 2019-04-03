package com.example.recorder.View

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recorder.Presenter.StatementPresenter
import com.example.recorder.R

class StatementAdapter : RecyclerView.Adapter<StatementViewHolder> {

    private var presenter : StatementPresenter

    constructor(presenter: StatementPresenter){
        this.presenter = presenter
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StatementViewHolder {
        return StatementViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.statement_list_item,p0,false),
            presenter
        )
    }

    override fun getItemCount(): Int {
        return presenter.getStatementsCount()
    }

    override fun onBindViewHolder(p0: StatementViewHolder, p1: Int) {
        presenter.onBindStatementItemView(p0,p1)
    }

}
