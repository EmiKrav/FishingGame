package com.example.fishinggame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter3 (val lifecycleOwner: LifecycleOwner,
private val viewModel: DataStoreViewModel, val Rites: List<Shop>):
RecyclerView.Adapter<ShopAdapter3.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        val Pavadinimas = view.findViewById(R.id.Pavadinimas) as TextView
        val Picture = view.findViewById(R.id.Picture) as ImageView
        val Button = view.findViewById(R.id.Buy) as Button
        val Price = view.findViewById(R.id.Price) as TextView


    }
    override fun getItemCount(): Int {
        return Rites.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_shop, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = Rites[position]
        holder.Pavadinimas.text = data.Pavadinimas
        holder.Price.text = data.Kaina.toString()
        holder.Picture.setImageResource(data.Image)
        holder.Picture.scaleType=ImageView.ScaleType.CENTER_INSIDE


        var p : Float = 0F
        var e : String = ""
        viewModel.getPinigai.observe(lifecycleOwner) {
            p = it
        }
        viewModel.getRites.observe(lifecycleOwner) {
            e = it
        }
        if (e.contains(holder.Pavadinimas.text) || holder.Pavadinimas.text == "Starter Rite" ){
            holder.Button.visibility = View.INVISIBLE
            holder.Button.isClickable = false
        }
        else{
            holder.Button.visibility = View.VISIBLE
            holder.Button.isClickable = true
        }

        holder.Button.setOnClickListener {
            if (data.Kaina <= p){
                viewModel.savePinigai(p -data.Kaina);
                holder.Button.visibility = View.INVISIBLE
                holder.Button.isClickable = false
                viewModel.saveRites(e + data.Pavadinimas + System.lineSeparator());
            }
        }
    }

}

