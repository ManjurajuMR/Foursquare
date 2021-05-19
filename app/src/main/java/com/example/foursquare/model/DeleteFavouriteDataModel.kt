package com.example.foursquare.model

import com.beust.klaxon.*

/*
private val klaxon = Klaxon()

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
}
*/



data class DelFavourite (
    private val status: Int,
    private val error: String,
    private val message: String,
    private val pageNo: Int,
    private val pageSize: Int,
    private val lastPage: Boolean,
    private val data: List<Favs>){

    fun gtStats() = status
    fun gtError() = error
    fun gtMesage() = message
    fun gtPageNo() = pageNo
    fun gtPageSize() = pageSize
    fun gtLastPage() = lastPage
    fun gtData() = data

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

    fun gtId() = id
    fun gtName() = name
    fun gtplaceType() = placeType
    fun gtoverallRating() = overallRating
    fun gtlatitude() = latitude
    fun gtlongitude() = longitude
    fun gtcost() = cost
    fun gtphone() = phone
    fun gtlandmark() = landmark
    fun gtaddress() = address
    fun gtoverview() = overview
    fun gtimage() = image

}

data class Favstype (
    val id: Int,
    val name: String) {

    fun gtfId() = id
    fun gtfname() = name

}


/*private val klaxon = Klaxon()

data class DelFavourite (
    private val status: Int,
    private val error: String,
    private val message: String,
    private val pageNo: Int,
    private val pageSize: Int,
    private val lastPage: Boolean,
    private val data: Any? = null){

    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<DelFavourite>(json)
    }

    fun gtStats() = status
    fun gtError() = error
    fun gtMesage() = message
    fun gtPageNo() = pageNo
    fun gtPageSize() = pageSize
    fun gtLastPage() = lastPage
    fun gtData() = data

}*/

