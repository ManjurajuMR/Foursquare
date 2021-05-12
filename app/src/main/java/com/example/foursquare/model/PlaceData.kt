package com.example.foursquare.model
import android.os.Parcel
import android.os.Parcelable
import com.beust.klaxon.*

private val klaxon = Klaxon()

data class PlaceData (
    val status: Long,
    val error: String,
    val message: String,
    val pageNo: Long,
    val pageSize: Long,
    val lastPage: Boolean,
    val data: List<Datum>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<PlaceData>(json)
    }
}

data class Datum (
    val place: Place,
    val distance: Double
) {

}

data class Place (
    val id: Long,
    val name: String,
    val placeType: List<PlaceType>,
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

data class PlaceType (
    val id: Long,
    val name: String
) {

}

