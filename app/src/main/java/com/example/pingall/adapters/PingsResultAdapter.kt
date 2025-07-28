package com.example.pingall.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pingall.R
import com.example.pingall.entities.PingResult

class PingsResultAdapter(private val pingsList: List<PingResult>) :
    RecyclerView.Adapter<PingsResultAdapter.PingsViewHolder>() {
    class PingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pingUrl: TextView = itemView.findViewById(R.id.pingUrl);
        val responseTime: TextView = itemView.findViewById(R.id.pingResponseTime);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ping_item, parent, false);
        return PingsViewHolder(view);
    }

    override fun getItemCount(): Int {
        return pingsList.size;
    }

    override fun onBindViewHolder(holder: PingsViewHolder, position: Int) {
        val ping = pingsList[position];
        holder.pingUrl.text = ping.url;
        holder.responseTime.text = "${ping.responseTime.toString()}ms";
    }
}