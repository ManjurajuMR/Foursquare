package com.example.foursquare.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foursquare.Home.Adapter.RecyclerviewAdapter
import com.example.foursquare.PhotoDetaisActivity
import com.example.foursquare.R
import com.example.foursquare.model.Photos

class PhotosAdapter(private var images:Photos, var mycontext: Context): RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.photos, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images.data[position]

        if (image != null) {
            Glide.with(mycontext).load(image.image).placeholder(R.drawable.loading).into(holder.photo)

            holder.photo.setOnClickListener {
                val intent=Intent(it.context,PhotoDetaisActivity::class.java)
                intent.putExtra("photoId",image.photoId)
                it.context.startActivity(intent)
                Toast.makeText(mycontext, "Item is clicked ${image.photoId}", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(mycontext, "Item is null", Toast.LENGTH_LONG).show()
        }


    }

    override fun getItemCount(): Int {
        return images.data.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val photo: ImageView = view.findViewById(R.id.photos)

    }
}