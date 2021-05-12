package com.example.foursquare.Home.Adapter.tools

import android.graphics.Color

class PriceRange {
    fun getRupeeIcon(cost:Long):String{
        return when{
            cost>=1 && cost<2 -> "$"
            cost>=2 && cost<3 -> "$$"
            cost>=3 && cost<4 -> "$$$"
            cost>=4 -> "$$$$"
            else -> ""
        }
    }
}