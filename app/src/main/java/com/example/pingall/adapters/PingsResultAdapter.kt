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
    private val onRemoveClicked: (String) -> Unit
) : RecyclerView.Adapter<PingsResultAdapter.PingsViewHolder>() {


    private val pingsList = mutableListOf<PingResult>()

    class PingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pingUrl: TextView = itemView.findViewById(R.id.pingUrl)
        val responseTime: TextView = itemView.findViewById(R.id.pingResponseTime)
        val removeButton: View = itemView.findViewById(R.id.buttonremover)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ping_item, parent, false)
        return PingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PingsViewHolder, position: Int) {
        val ping = pingsList[position]
        holder.pingUrl.text = ping.url

        val responseTimeValue = ping.responseTime
        if (responseTimeValue != null) {
            holder.responseTime.text = "${responseTimeValue}ms"
        } else {
            holder.responseTime.text = "Pinging..."
        }

        holder.removeButton.setOnClickListener {
            onRemoveClicked(ping.url)
        }
    }


    override fun getItemCount(): Int = pingsList.size

    fun updateList(newList: List<PingResult>) {
        pingsList.clear()
        pingsList.addAll(newList)
        notifyDataSetChanged()
    }


}
