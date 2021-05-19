package com.example.foursquare.Home.Adapter

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foursquare.Home.Adapter.tools.PriceRange
import com.example.foursquare.Home.Adapter.tools.RatingBackground
import com.example.foursquare.R
import com.example.foursquare.authentication.Constents
import com.example.foursquare.model.Datum
import com.example.foursquare.model.FavPdata
import com.example.foursquare.model.Place
import com.example.foursquare.viewmodel.FavouritesViewModel


class RecyclerviewAdapter(private var arrayList: List<Datum>,private val mycontext: Context, private val clickListener: OnSiteItemClickListener) : RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {
    private val favouriteViewModel = ViewModelProvider.AndroidViewModelFactory(mycontext.applicationContext as Application).create(FavouritesViewModel::class.java)
    var favouriteData : List<FavPdata>?=null
    val sharedPreferences = mycontext.getSharedPreferences(Constents.Shared_pref, AppCompatActivity.MODE_PRIVATE)
    init {
        if(sharedPreferences.contains(Constents.USER_ID)) {
            getFavourite()
        }
    }
    inner class ViewHolder(view: View, private val onClickListener: OnSiteItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val textView1: TextView = view.findViewById(R.id.restname)
        val textView2: TextView = view.findViewById(R.id.rating)
        val textView3: TextView = view.findViewById(R.id.type)
        val textView4: TextView = view.findViewById(R.id.address)
        val textView5: TextView = view.findViewById(R.id.price_range)
        val textView6: TextView = view.findViewById(R.id.distance)
        val imageView : ImageView = view.findViewById(R.id.restimage)
        val favourite :ToggleButton = view.findViewById(R.id.toggle1)
        val cardView : CardView = view.findViewById(R.id.ratingBackground)

        init {
            itemView.setOnClickListener(this)
            favourite.setOnClickListener {
                if (favourite.isChecked){
                    favourite.isChecked=false
                }else{
                    favourite.isChecked=true
                }
            }
        }

        override fun onClick(p0: View?) {
            onClickListener.onSiteClick(arrayList[adapterPosition].place.id,arrayList[adapterPosition].distance)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.homerecyclerview_item, parent, false)
        return ViewHolder(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrayList = arrayList[position]

        if (arrayList!=null) {
            val isFavourite = checkIsFavourite(arrayList.place.id)
            if(isFavourite)
                holder.favourite.isChecked = true

            holder.textView1.text = arrayList.place.name
            val rating = arrayList.place.overallRating
            holder.textView4.text = arrayList.place.landmark
            holder.textView3.text=arrayList.place.placeType[0].name
            val cost=arrayList.place.cost
            val priceRange=PriceRange().getRupeeIcon(cost)
            holder.textView5.text=priceRange
            holder.textView6.text=String.format("%.1f km", arrayList.distance)
            holder.textView2.text = String.format("%.1f",rating)
            val ratingBackground = RatingBackground().getRatingColor(rating)
            holder.cardView.background.setTint(ratingBackground)
            Glide.with(holder.imageView).load(arrayList.place.image).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(holder.imageView)
        }else{
            Toast.makeText(mycontext, "Item is null", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnSiteItemClickListener {
        fun onSiteClick(PlaceId: Long,distence:Double)
    }




    private fun getFavourite() {
        val userId=sharedPreferences.getString(Constents.USER_ID,"")
        val token=sharedPreferences.getString(Constents.USER_TOKEN,"")
        if(userId!=null && token!=null) {
            val token = "Bearer $token"
            val pageNumber = 0
            val pageSize = 200
            val owner = (mycontext as AppCompatActivity)
            favouriteViewModel.getFavouritesByUserId(token,userId,pageNumber,pageSize,).observe(owner,{
                if(it!=null) {
                    if (it.status == 200) {
                        favouriteData = it.data
                        notifyDataSetChanged()
                    }
                }
            })
        }
    }

    private fun checkIsFavourite(placeId: Long) : Boolean {
        var isFavourite = false
        if(favouriteData!=null){
            for( data in favouriteData!!){
                if(data.id == placeId) {
                    isFavourite = true
                    break
                }
            }
        }
        return isFavourite
    }

}

