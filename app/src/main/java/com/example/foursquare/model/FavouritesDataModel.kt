package com.example.foursquare.model

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Favourites (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: List<FavPdata>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Favourites>(json)
    }
}

data class FavPdata (
    val id: Long,
    val name: String,
    val placeType: List<FavPlaceType>,
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

data class FavPlaceType (
    val id: Long,
    val name: String
)
