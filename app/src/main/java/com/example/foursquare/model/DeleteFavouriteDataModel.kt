package com.example.foursquare.model

import com.beust.klaxon.*

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

    fun gstatus() = status
    fun gMessage() = message

    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<DelFavourite>(json)
    }
}
