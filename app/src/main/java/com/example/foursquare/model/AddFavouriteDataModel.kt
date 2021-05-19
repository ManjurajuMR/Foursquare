package com.example.foursquare.model

import com.beust.klaxon.*
import com.google.gson.annotations.SerializedName

//private val klaxon = Klaxon()

//data class FavData (
//
//    @SerializedName("timestamp") private  val timestamp : String,
//    //val timestamp: String,
//    @SerializedName("status") private val status : Int,
//    //val status: Long,
//    @SerializedName("error") val error : String,
//    @SerializedName("message") private  val message : String,
////    val error: String,
////    val message: String,
//    //val path: String
//    @SerializedName("path") private  val path : String
//
//) {
//    public fun toJson() = klaxon.toJsonString(this)
//
//    companion object {
//        public fun fromJson(json: String) = klaxon.parse<FavData>(json)
//    }
//
//    fun getStatus() = status
//    fun getMessage() = message
//}

/*data class Addfav (
    private val status: Int,
    private val error : String,
    private val message: String,
    private val pageNo: Int,
    private val pageSize: Int,
    private val lastPage: Boolean,
    private val data: List<AddfavData>) {

    fun getStatu() = status
    fun getError() = error
    fun getMessag() = message
    fun getPageNo() = pageNo
    fun getPageSize() = pageSize
    fun getLastPage() = lastPage
    fun getData() = data
}

data class AddfavData(
    private val id: Int,
    private val name: String,
    private val placeType: List<AddFavtype>,
    private val overallRating: Double,
    private val latitude: Double,
    private val longitude: Double,
    private val cost: Long,
    private val phone: Long,
    private val landmark: String,
    private val address: String,
    private val overview: String,
    private val image: String) {

    fun getId() = id
    fun getName() = name
    fun getplaceType() = placeType
    fun getoverallRating() = overallRating
    fun getlatitude() = latitude
    fun getlongitude() = longitude
    fun getcost() = cost
    fun getphone() = phone
    fun getlandmark() = landmark
    fun getaddress() = address
    fun getoverview() = overview
    fun getimage() = image

}

data class AddFavtype (
    val id: Int,
    val name: String) {

    fun getfId() = id
    fun getfname() = name

}*/


import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Addfav (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: AddfavData
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Addfav>(json)
    }
}

data class AddfavData (
    @Json(name = "fav_id")
    val favID: Long,

    @Json(name = "user_id")
    val userID: Long,

    @Json(name = "place_id")
    val placeID: Long
)
