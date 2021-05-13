package com.example.foursquare.model

import com.google.gson.annotations.SerializedName
import com.beust.klaxon.*
/*
data class Place(

    @SerializedName("status") private val status : Int,
    @SerializedName("error") val error : String,
    @SerializedName("message") private  val message : String,
    @SerializedName("pageNo") val pageNo : Int,
    @SerializedName("pageSize") val pageSize : Int,
    @SerializedName("lastPage") val lastPage : Boolean,
    @SerializedName("data") val data : Pdata) {

    //fun getStatus() = status
    //fun getMessage() = message
    //fun getData() = data
}

data class Pdata(

    @SerializedName("id")
    val placeId : Int,
    @SerializedName("name")
    val placeName : String,
    val overallRating : Float,
    val latitude : Double,
    val longitude : Double,
    val cost : Int,
    val phone : Long,
    val landmark : String,
    val address : String,
    val overview : String,
    val image : String,
    val placeType: PlaceType) {

    */
/*fun getplaceId() = placeId
    fun getplaceName() = placeName
    fun getoverallRating() = overallRating
    fun getlatitude() = latitude
    fun getlongitude() = longitude
    fun getcost() = cost
    fun getphone() = phone
    fun getlandmark() = landmark
    fun getaddress() = address
    fun getoverview() = overview
    fun getimage() = image
    fun getplaceType() = placeType*//*


}

data class PlaceType(

    @SerializedName("id")
    val ptypeId : Int,
    @SerializedName("name")
    val ptypeName : String) {

    //fun getptytpeId() = ptypeId
    //fun getptypeName() = ptypeName

}*/

private val klaxon = Klaxon()

data class Rplace (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: Pdata
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Rplace>(json)
    }
}

data class Pdata (
    val id: Long,
    val name: String,
    val placeType: List<RplaceType>,
    val overallRating: Double,
    val latitude: Double,
    val longitude: Double,
    val cost: Long,
    val phone: Long,
    val landmark: String,
    val address: String,
    val overview: String,
    val image: String
)

data class RplaceType (
    val id: Long,
    val name: String
)
