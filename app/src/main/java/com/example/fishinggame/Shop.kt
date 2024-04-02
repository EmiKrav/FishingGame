package com.example.fishinggame

import androidx.annotation.DrawableRes

data class Shop(val Pavadinimas: String, val Kaina: Float, var Nupirkti: Boolean, @DrawableRes val Image: Int) {

}