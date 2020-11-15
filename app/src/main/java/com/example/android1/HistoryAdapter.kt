package com.example.android1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter (private val history: ArrayList<historyRecord>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>()
{

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val historyLine1 = itemView.findViewById<TextView>(R.id.line1)
        val historyLine2 = itemView.findViewById<TextView>(R.id.line2)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(contactView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: HistoryAdapter.ViewHolder, position: Int) {
        val historyRow: historyRecord = history.get(position)
        val textView1 = viewHolder.historyLine1
        textView1.setText((position + 1).toString() + " pomiar:")
        val textView2 = viewHolder.historyLine2
        textView2.setText(historyRow.getLine1())

    }

    override fun getItemCount(): Int {
        return history.size
    }
}