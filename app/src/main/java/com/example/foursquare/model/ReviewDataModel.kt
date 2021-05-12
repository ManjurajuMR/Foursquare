package com.example.foursquare.model

import com.google.gson.annotations.SerializedName

data class ReviewBase (

    @SerializedName("status") val status : Int,
    @SerializedName("error") val error : String,
    @SerializedName("message") val message : String,
    @SerializedName("pageNo") val pageNo : Int,
    @SerializedName("pageSize") val pageSize : Int,
    @SerializedName("lastPage") val lastPage : Boolean,
    @SerializedName("data") val data : List<Review>
)

data class Review (

    @SerializedName("placeId") val placeId : Int,
    @SerializedName("placeName") val placeName : String,
    @SerializedName("userImage") val userImage : String,
    @SerializedName("userName") val userName : String,
    @SerializedName("review") val review : String,
    @SerializedName("date") val date : String
){
    val getPlaceId = placeId
    val getPlaceName = placeName
    val getUserImage = userImage
    val getUserName = userName
    val getReview = review
    val getDate = date
}