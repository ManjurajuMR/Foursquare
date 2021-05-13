package com.example.foursquare.model

import com.beust.klaxon.*
import com.example.foursquare.services.AddReviewApi

private val klaxon = Klaxon()

data class ReviewData (
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
        public fun fromJson(json: String) = klaxon.parse<AddReviewApi>(json)
    }
}
