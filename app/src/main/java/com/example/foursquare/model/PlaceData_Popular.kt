package com.example.foursquare.model

import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

data class Popular_PlaceData (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: List<Datum1>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Popular_PlaceData>(json)
    }
}

data class Datum1 (
    val id: Long,
    val name: String,
    val placeType: List<PlaceType1>,
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

data class PlaceType1 (
    val id: Long,
    val name: String
)
