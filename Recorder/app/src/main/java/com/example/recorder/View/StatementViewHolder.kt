package com.example.recorder.View

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import com.example.recorder.Presenter.StatementItemView
import kotlinx.android.synthetic.main.statement_list_item.view.*


class StatementViewHolder (view: View) :
    RecyclerView.ViewHolder(view), StatementItemView {

    override fun setStatement(text: String) {
        etStatement.setText(text)
    }

    val etStatement : EditText = view.etStatement

    

}