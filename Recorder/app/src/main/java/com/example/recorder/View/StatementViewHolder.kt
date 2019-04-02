package com.example.recorder.View

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import com.example.recorder.Presenter.StatementItemView
import com.example.recorder.Presenter.StatementPresenter
import com.example.recorder.R
import kotlinx.android.synthetic.main.statement_list_item.view.*
import kotlin.coroutines.coroutineContext


class StatementViewHolder (view: View,private val  presenter : StatementPresenter) :
    RecyclerView.ViewHolder(view), StatementItemView {

    private val etStatement : EditText = view.etStatement
    private val btnCancellChange : ImageButton = view.cancellButton
    private var textBeforeChange : String = etStatement.toString()

    override fun setStatement(text: String) {
        etStatement.setText(text)
    }

    override fun setOnTextChanged() {
        etStatement.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                btnCancellChange.setBackgroundColor(ContextCompat.getColor(btnCancellChange.context,R.color.colorPrimary))
                btnCancellChange.isEnabled = true
                presenter.manageTextChange(etStatement.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
    }

    override fun setCancellButtonOnClickListener() {
        btnCancellChange.setOnClickListener(View.OnClickListener {
            setStatement(textBeforeChange)

            disableButton()
        })
    }

    override fun disableButton() {
        btnCancellChange.isEnabled = false
        btnCancellChange.setBackgroundColor(ContextCompat.getColor(btnCancellChange.context,R.color.colorGray))
    }

}