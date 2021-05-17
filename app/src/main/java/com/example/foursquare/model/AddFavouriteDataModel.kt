package com.example.foursquare.model

import com.beust.klaxon.*
import com.google.gson.annotations.SerializedName

private val klaxon = Klaxon()

data class FavData (

    @SerializedName("timestamp") private  val timestamp : String,
    //val timestamp: String,
    @SerializedName("status") private val status : Int,
    //val status: Long,
    @SerializedName("error") val error : String,
    @SerializedName("message") private  val message : String,
//    val error: String,
//    val message: String,
    //val path: String
    @SerializedName("path") private  val path : String

) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<FavData>(json)
    }

    fun getStatus() = status
    fun getMessage() = message
}