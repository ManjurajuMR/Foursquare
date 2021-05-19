package com.example.foursquare.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foursquare.Home.Adapter.tools.PriceRange
import com.example.foursquare.Home.Adapter.tools.RatingBackground
import com.example.foursquare.PhotoDetaisActivity
import com.example.foursquare.R
import com.example.foursquare.model.FavPdata
import com.example.foursquare.model.Photos
import com.example.foursquare.model.ReviewPhotos


class AddReviewPhotoAdapter(val capturedPhotos: ArrayList<ReviewPhotos>) : RecyclerView.Adapter<AddReviewPhotoAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val addReviewPhoto=itemView.findViewById<ImageView>(R.id.add_review_photo)


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_add_review_photos, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val reviewPhoto=capturedPhotos[position]
            holder.addReviewPhoto.setImageURI(reviewPhoto.image)
        }

        override fun getItemCount(): Int {
            return capturedPhotos.size
        }


}