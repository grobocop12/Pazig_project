package com.example.recorder.View

import android.app.AlertDialog
import android.content.DialogInterface
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


class StatementViewHolder(view: View, private val presenter: StatementPresenter) : RecyclerView.ViewHolder(view),
    StatementItemView {
    private val etStatement: EditText = view.etStatement
    private val btnCancelChange: ImageButton = view.cancelButton
    public val btnDeleteStatement: ImageButton = view.deleteButton
    private var textBeforeChange: String? = null
    private var index: Int = -1

    override fun setStatement(text: String?) {
        etStatement.setText(text)
    }

    override fun setOnTextChanged() {

        etStatement.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {


            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.manageTextChange(etStatement.text.toString(), index)
                enableButton()
            }

        })
    }

    override fun setCancelButtonOnClickListener() {
        btnCancelChange.setOnClickListener {
            setStatement(textBeforeChange)
            enableButton()
        }
    }

    override fun enableButton() {
        if (etStatement.text.toString() == textBeforeChange) {
            btnCancelChange.isEnabled = false
            btnCancelChange.setBackgroundColor(ContextCompat.getColor(btnCancelChange.context, R.color.colorGray))
        } else {
            btnCancelChange.setBackgroundColor(ContextCompat.getColor(btnCancelChange.context, R.color.colorPrimary))
            btnCancelChange.isEnabled = true
        }
    }

    override fun setUnchangedText(text: String) {
        textBeforeChange = text
    }

    override fun setIndex(newIndex: Int) {
        index = newIndex
    }

    override fun deleteStatement() {
        /*
        val builder = AlertDialog.Builder(itemView.context)
        builder.setMessage(R.string.alertDialogMessage)
        builder.setCancelable(true)
        builder.setPositiveButton(
            R.string.alertDialogButtonPositive
        ) { _: DialogInterface, _: Int ->

        }
        builder.setNegativeButton(R.string.alertDialogButtonNegative
        ) { _,_ ->
        }
        builder.create()
        builder.show()
        */
        presenter.deleteStatement(index)
    }
}