package com.example.fishinggame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

class EgzoSkritulys @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {



    private fun getRandomColor(): Int {
        return Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }
    private fun RandomSize(): Int {
        return Random.nextInt(250)
    }


    var paint = Paint().apply {
        color = getRandomColor()
    }
    var paint2 = Paint().apply {
        color = getRandomColor()
    }

    var radius = RandomSize().toFloat()
    val randintx = Random.nextInt(radius.toInt(),
        (getContext().resources.displayMetrics.widthPixels-radius).toInt()
    )
    val randinty = Random.nextInt(radius.toInt(),
        (getContext().resources.displayMetrics.heightPixels-radius).toInt()
    )
    private val centre = PointF(randintx.toFloat(), randinty.toFloat())



    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(centre.x, centre.y, radius, paint)
        canvas.drawCircle(centre.x, centre.y, radius/1.1F, paint2)
    }


}
