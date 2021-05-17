package com.example.foursquare.model

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("status") private val status : Int,
    @SerializedName("error") private val error : String,
    @SerializedName("message") private  val message : String,
    @SerializedName("pageNo") private val pageNo : Int,
    @SerializedName("pageSize") private val pageSize : Int,
    @SerializedName("lastPage") private val lastPage : Boolean,
    @SerializedName("data") private val data : Any
){
    fun getStatus() = status
    fun getMessage() = message
    fun getData() = data
}

/*
private val klaxon = Klaxon()

data class Welcome1 (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: Double
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Welcome1>(json)
    }
}
*/
