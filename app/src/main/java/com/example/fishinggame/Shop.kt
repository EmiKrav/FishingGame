package com.example.fishinggame

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class Shop(val Pavadinimas: String, val Kaina: Float, var Nupirkti: Boolean, @DrawableRes val Image: Int) {

}