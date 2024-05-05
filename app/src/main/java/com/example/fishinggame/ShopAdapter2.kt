package com.example.fishinggame

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

var scaleFactor = 1f
class ShopAdapter2(val lifecycleOwner: LifecycleOwner,
                   private val viewModel: DataStoreViewModel, val rods: List<Shop>):
    RecyclerView.Adapter<ShopAdapter2.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        val Pavadinimas = view.findViewById(R.id.Pavadinimas) as TextView
        val Picture = view.findViewById(R.id.Picture) as ImageView
        val Button = view.findViewById(R.id.Buy) as Button
        val Price = view.findViewById(R.id.Price) as TextView



    }
    override fun getItemCount(): Int {
        return rods.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(

            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_shop, parent, false)

        )
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = rods[position]
        holder.Pavadinimas.text = data.Pavadinimas
        holder.Price.text = data.Kaina.toString()
        holder.Picture.setImageResource(data.Image)
        holder.Picture.scaleType=ImageView.ScaleType.CENTER_INSIDE



        var p : Float = 0F
        var e : String = ""
        viewModel.getPinigai.observe(lifecycleOwner) {
            p = it
        }
        viewModel.getMeskeres.observe(lifecycleOwner) {
            e = it
        }
        if (e.contains(holder.Pavadinimas.text) || holder.Pavadinimas.text == "Starter Rod" ){
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
                viewModel.saveMeskeres(e + data.Pavadinimas + System.lineSeparator());
            }
        }


        var scale_g_detector: ScaleGestureDetector =
            ScaleGestureDetector(holder.Picture.context, ScaleListener(holder.Picture))
        var duration : Long? = null
        holder.Picture.setOnTouchListener { v, event ->
        scale_g_detector.onTouchEvent(event);
            if (event.action == MotionEvent.ACTION_DOWN) {
                duration = System.currentTimeMillis()
            }
            if (event.action == MotionEvent.ACTION_UP) {
                if (duration != null && System.currentTimeMillis() - duration!! >= 1000) {
                    holder.Picture.performLongClick()
                }
            }
            true
        };
        holder.Picture.setOnLongClickListener {


            holder.Picture.scaleX = 1F
            holder.Picture.scaleY = 1F
            true
        }
    }


    class ScaleListener(picture: ImageView) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        var img = picture
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = Math.max(1f, Math.min(scaleFactor, 10.0f))
            img.scaleX = 2 * scaleFactor
            img.scaleY = scaleFactor
            return true
        }

    }


}

