package com.example.fishinggame

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.SoundPool
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

var scaleFactor4 = 1F;
class ShopAdapter4 (val lifecycleOwner: LifecycleOwner,
                    private val viewModel: DataStoreViewModel, val pludes: List<Shop>):
RecyclerView.Adapter<ShopAdapter4.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        val Pavadinimas = view.findViewById(R.id.Pavadinimas) as TextView
        val Picture = view.findViewById(R.id.Picture) as ImageView
        val Button = view.findViewById(R.id.Buy) as Button
        val Price = view.findViewById(R.id.Price) as TextView


    }
    override fun getItemCount(): Int {
        return pludes.size
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
        val data = pludes[position]
        holder.Pavadinimas.text = data.Pavadinimas
        holder.Price.text = data.Kaina.toString()
        holder.Picture.setImageResource(data.Image)
        holder.Picture.scaleType=ImageView.ScaleType.CENTER_INSIDE


        var p : Float = 0F
        var e : String = ""
        viewModel.getPinigai.observe(lifecycleOwner) {
            p = it
        }
        viewModel.getPludes.observe(lifecycleOwner) {
            e = it
        }
        if (e.contains(holder.Pavadinimas.text) || holder.Pavadinimas.text == "Starter Plude" ){
            holder.Button.visibility = View.INVISIBLE
            holder.Button.isClickable = false
        }
        else{
            holder.Button.visibility = View.VISIBLE
            holder.Button.isClickable = true
        }
        var soundPool : SoundPool?= SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        var soundId = soundPool?.load(holder.Button.context, com.example.fishinggame.R.raw.buying, 1)


        holder.Button.setOnClickListener {
            if (data.Kaina <= p){
                soundPool?.play(soundId!!, 1F, 1F, 0, 0, 1F)

                viewModel.savePinigai(p -data.Kaina);
                holder.Button.visibility = View.INVISIBLE
                holder.Button.isClickable = false
                viewModel.savePludes(e + data.Pavadinimas + System.lineSeparator());
            }
        }
//        var scale_g_detector: ScaleGestureDetector =
//            ScaleGestureDetector(holder.Picture.context, ShopAdapter2.ScaleListener(holder.Picture))
//        var duration : Long? = null

        val action = ShopFragmentDirections.actionShopFragmentToShopBigItemFragment()


//        holder.Picture.setOnTouchListener { v, event ->
//            scale_g_detector.onTouchEvent(event);
//            if (event.action == MotionEvent.ACTION_DOWN) {
//                duration = System.currentTimeMillis()
//            }
//            if (event.action == MotionEvent.ACTION_UP) {
//                if (duration != null && System.currentTimeMillis() - duration!! >= 1000) {
//                    holder.Picture.performLongClick()
//                }
//            }
//            true
//        };
        holder.Picture.setOnLongClickListener {view ->
            view.findNavController().navigate(action)
            Data.putPicture(holder.Picture.drawable)
            Data.putPosition(position)
//            holder.Picture.scaleX = 1F
//            holder.Picture.scaleY = 1F
            true
        }
    }
//    class ScaleListener(picture: ImageView) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//        var img = picture
//        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
//            scaleFactor4 *= scaleGestureDetector.scaleFactor
//            scaleFactor4 = Math.max(1f, Math.min(scaleFactor4, 10.0f))
//            img.scaleX = scaleFactor4
//            img.scaleY = scaleFactor4
//            return true
//        }
//
//    }

}

