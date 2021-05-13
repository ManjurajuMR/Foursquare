package com.example.foursquare.model

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class UserReviews (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: List<Reviews>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<UserReviews>(json)
    }
}

data class Reviews (
    @Json(name = "placeId")
    val placeID: Long,

    val placeName: String,
    val userImage: String? = null,
    val userName: String,
    val review: String,
    val date: String
)
