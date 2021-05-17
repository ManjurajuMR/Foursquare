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

data class Addfav (
    private val status: Int,
    private val message: String,
    private val pageNo: Int,
    private val pageSize: Int,
    private val lastPage: Boolean,
    private val data: List<AddfavData>) {

    fun getStatus() = status
    fun getMessage() = message
    fun getPageNo() = pageNo
    fun getPageSize() = pageSize
    fun getLastPage() = lastPage
    fun getData() = data
}

data class AddfavData(
    private val placeId : Int,
    private val placeName : String,
    private val userImage : String,
    private val userName : String,
    private val review : String,
    private val date : String ) {

    fun getPlaceId() = placeId
    fun getPlaceName() = placeName
    fun getUserImage() = userImage
    fun getUserName() = userName
    fun getReview() = review
    fun getDate() = date

}