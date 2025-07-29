package com.example.pingall.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pingall.R
import com.example.pingall.entities.PingResult

class PingsResultAdapter(
    private val onRemoveClick: (PingResult) -> Unit
) : RecyclerView.Adapter<PingsResultAdapter.PingsViewHolder>() {

    private val pingsList = mutableListOf<PingResult>()

    inner class PingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pingUrl: TextView = itemView.findViewById(R.id.pingUrl)
        val responseTime: TextView = itemView.findViewById(R.id.pingResponseTime)
        val buttonRemover: Button = itemView.findViewById(R.id.buttonremover)

        fun bind(ping: PingResult) {
            pingUrl.text = ping.url
            responseTime.text = ping.responseTime?.let { "${it}ms" } ?: "Pinging..."
            buttonRemover.setOnClickListener {
                onRemoveClick(ping)
                removeItem(ping)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ping_item, parent, false)
        return PingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PingsViewHolder, position: Int) {
        holder.bind(pingsList[position])
    }

    override fun getItemCount(): Int = pingsList.size

    fun updateList(newList: List<PingResult>) {
        pingsList.clear()
        pingsList.addAll(newList)
        notifyDataSetChanged()
    }
    fun removeItem(ping: PingResult) {
        val index = pingsList.indexOfFirst { it.url == ping.url }
        if (index != -1) {
            pingsList.removeAt(index)
            notifyItemRemoved(index)
        }
    }

}
