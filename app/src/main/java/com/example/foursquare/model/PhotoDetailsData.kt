package com.example.foursquare.model

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.google.gson.annotations.SerializedName

private val klaxon = Klaxon()

data class PhotoDetails (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: Data_PhotoDetails
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<PhotoDetails>(json)
    }
}

data class Data_PhotoDetails (
   /* @Json(name = "photoId")
    val photoID: Long,

    val placeName: String,
    val userName: String,
    val userImage: String,
    val date: String,

    @Json(name = "photoUrl")
    val photoURL: String*/
    @SerializedName("photoId") val photoId : Int,
    @SerializedName("placeName") val placeName : String,
    @SerializedName("userName") val userName : String,
    @SerializedName("userImage") val userImage : String,
    @SerializedName("date") val date : String,
    @SerializedName("photoUrl") val photoUrl: String
        )
