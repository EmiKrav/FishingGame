package com.example.fishinggame

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


var scaleFactor : Float = 1F;

class ShopAdapter(val lifecycleOwner: LifecycleOwner,
                  private val viewModel: DataStoreViewModel, val lakes: List<Shop>):
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        val Pavadinimas = view.findViewById(R.id.Pavadinimas) as TextView
        val Picture = view.findViewById(R.id.Picture) as ImageView
        val Button = view.findViewById(R.id.Buy) as Button
        val Price = view.findViewById(R.id.Price) as TextView

        val consl = view.findViewById(R.id.PictureConst) as ConstraintLayout
        val screenWidth = consl.width
        val screenHeight = consl.height


    }
    override fun getItemCount(): Int {
        return lakes.size
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
        val data = lakes[position]
        holder.Pavadinimas.text = data.Pavadinimas
        holder.Price.text = data.Kaina.toString()
        holder.Picture.setImageResource(data.Image)


        var p: Float = 0F
        var e: String = ""
        viewModel.getPinigai.observe(lifecycleOwner) {
            p = it
        }
        viewModel.getEzerai.observe(lifecycleOwner) {
            e = it
        }
        if (e.contains(holder.Pavadinimas.text) || holder.Pavadinimas.text == "Beginer's Bridge") {
            holder.Button.visibility = View.INVISIBLE
            holder.Button.isClickable = false
        } else {
            holder.Button.visibility = View.VISIBLE
            holder.Button.isClickable = true
        }
        var soundPool: SoundPool? = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        var soundId =
            soundPool?.load(holder.Button.context, com.example.fishinggame.R.raw.buying, 1)

        holder.Button.setOnClickListener {
            if (data.Kaina <= p) {

                soundPool?.play(soundId!!, 1F, 1F, 0, 0, 1F)
                viewModel.savePinigai(p - data.Kaina);
                holder.Button.visibility = View.INVISIBLE
                holder.Button.isClickable = false
                viewModel.saveEzerai(e + data.Pavadinimas + System.lineSeparator());
            }
        }
//        var scale_g_detector: ScaleGestureDetector =
//            ScaleGestureDetector(holder.Picture.context, ShopAdapter2.ScaleListener(holder.Picture))
//        var duration: Long? = null


        val action = ShopFragmentDirections.actionShopFragmentToShopBigItemFragment()

        holder.Picture.setOnLongClickListener { view ->
            view.findNavController().navigate(action)
            Data.putPicture(holder.Picture.drawable)
            Data.putPosition(position)

            //findNavController().navigate(action)

            //  holder.Picture.scaleX = 1F
            // holder.Picture.scaleY = 1F
            //   holder.Picture.x = 0F;
            //  holder.Picture.y = 0F;
            true
        }
    }
//        var xDown = 0f
//        var yDown = 0f
//
//        holder.Picture.setOnTouchListener { v, event ->
//
//            if ( event.pointerCount < 2) {
//
//                if (event.action == MotionEvent.ACTION_MOVE) {
//                    val xMove: Float = event.x
//                    val yMove: Float = event.y
//
//                    val distX: Float = xMove - xDown
//                    val distY: Float = yMove - yDown
//
//                    if (holder.Picture.x + distX < holder.screenWidth) {
//                        holder.Picture.x = holder.Picture.x + distX
//                    }
//                    if (holder.Picture.y + distY < holder.screenHeight) {
//                        holder.Picture.y = holder.Picture.y + distY
//                    }
//
//                }
//                if (event.action == MotionEvent.ACTION_DOWN) {
//                    duration = System.currentTimeMillis()
//                    xDown = event.x;
//                    yDown = event.y;
//                }
//                if (event.action == MotionEvent.ACTION_UP) {
//                    if (duration != null && System.currentTimeMillis() - duration!! >= 1000) {
//                        holder.Picture.performLongClick()
//                    }
//                }
//            }
//            if(event.pointerCount > 1){
//                scale_g_detector.onTouchEvent(event);
//            }
//            true
//        };
//    }
//    class ScaleListener(picture: ImageView) : ScaleGestureDetector.SimpleOnScaleGestureListener() {
//        var img = picture
//        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
//            scaleFactor *= scaleGestureDetector.scaleFactor
//            scaleFactor = Math.max(1f, Math.min(scaleFactor, 10.0f))
//
//            img.scaleX = scaleFactor
//            img.scaleY = scaleFactor
//            return true
//        }
//
//    }

}

