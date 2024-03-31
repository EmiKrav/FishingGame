package com.example.fishinggame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter (val fishes: List<History>):
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
     //   val Pinigai = view.findViewById(R.id.Pinigai) as TextView
    ///    val Kiekis = view.findViewById(R.id.Kiekis) as TextView
        val Laikas = view.findViewById(R.id.Laikas) as TextView
    }
    override fun getItemCount(): Int {
        return fishes.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_history, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = fishes[position]
      //  holder.Pinigai.text = data.Pinigai.toString()
     //   holder.Kiekis.text = data.Kiekis.toString()
        holder.Laikas.text = data.Laikas
    }
}