package com.example.foursquare.model

import com.beust.klaxon.*
import com.google.gson.annotations.SerializedName

private val klaxon = Klaxon()

data class Photos (
    val status: Int,
    val error: String,
    val message: String,
    val pageNo: Int,
    val pageSize: Int,
    val lastPage: Boolean,
    val data: List<Datum_photos>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Photos>(json)
    }
}

data class Datum_photos (
  /*  val photoid: Int,
    val userid: Int,
    val placeid: Int,
    val image: String,
    val date: String*/
    @SerializedName("photo_id") val photoId : Int,
    @SerializedName("user_id") val userId : Int,
    @SerializedName("place_id") val placeId : Int,
    @SerializedName("image") val image : String,
    @SerializedName("date") val date : String
)