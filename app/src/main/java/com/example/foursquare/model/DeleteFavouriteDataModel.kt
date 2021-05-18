package com.example.foursquare.model

import com.beust.klaxon.*

/*private val klaxon = Klaxon()

data class DelFavourite (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: Any? = null
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<DelFavourite>(json)
    }
}*/


data class DelFavourite (
    private val status: Int,
    private val error: String,
    private val message: String,
    private val pageNo: Int,
    private val pageSize: Int,
    private val lastPage: Boolean,
    private val data: List<Favs>){

    fun getStats() = status
    fun getError() = error
    fun getMesage() = message
    fun getPageNo() = pageNo
    fun getPageSize() = pageSize
    fun getLastPage() = lastPage
    fun getData() = data

}

data class Favs (
    private val id: Int,
    private val name: String,
    private val placeType: List<Favstype>,
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

data class Favstype (
    val id: Int,
    val name: String) {

    fun getfId() = id
    fun getfname() = name

}