package com.example.foursquare.Home.Adapter.tools

import android.graphics.Color
import androidx.annotation.ColorRes

class RatingBackground {
    fun getRatingColor(rating : Double) : Int{
        return when{
            (rating<=2.5) -> Color.parseColor("#FF0000")
            rating>2.5 && rating<= 5.0 -> Color.parseColor("#FFD700")
            rating>5.0 && rating<=7.5 -> Color.parseColor("#76ff03")
            else -> Color.parseColor("#36B000")
        }
       /* return when{
            (rating<=2.5) -> "@color/rating2.5"
            rating>2.5 && rating<= 5.0 -> "@color/rating2.5to5.0"
            rating>5.0 && rating<=7.5 -> "@color/rating5.0to7.5"
            else -> "@color/rating_above7.5"
        }*/
    }
}