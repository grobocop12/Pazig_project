package com.example.recorder.View

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recorder.Presenter.StatementPresenter
import com.example.recorder.R

class StatementAdapter : RecyclerView.Adapter<StatementViewHolder> {

    private var presenter: StatementPresenter

    constructor(presenter: StatementPresenter) {
        this.presenter = presenter
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): StatementViewHolder {
        val holder = StatementViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.statement_list_item, p0, false),
            presenter
        )

        holder.btnDeleteStatement.setOnClickListener {
            val builder = AlertDialog.Builder(holder.btnDeleteStatement.context)
            builder.setMessage(R.string.alertDialogMessage)
            builder.setCancelable(true)
            builder.setPositiveButton(
                R.string.alertDialogButtonPositive
            ) { _: DialogInterface, _: Int ->
                holder.deleteStatement()
                notifyDataSetChanged()
            }
            builder.setNegativeButton(
                R.string.alertDialogButtonNegative
            ) { _, _ ->
            }
            builder.create()
            builder.show()
        }
        return holder
    }

    override fun getItemCount(): Int {
        return presenter.getStatementsCount()
    }

    override fun onBindViewHolder(p0: StatementViewHolder, p1: Int) {
        presenter.onBindStatementItemView(p0, p1)
    }

}
