package com.example.foursquare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foursquare.Home.Adapter.tools.PriceRange
import com.example.foursquare.Home.Adapter.tools.RatingBackground
import com.example.foursquare.R
import com.example.foursquare.model.Datum
import com.example.foursquare.model.FavPdata

class FavouritesAdapter(private var arrayList: List<FavPdata>, private val clickListener: OnFavItemClickListener) : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View, private val onClickListener: OnFavItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val textView1: TextView = view.findViewById(R.id.frestname)
        val textView2: TextView = view.findViewById(R.id.frating)
        val textView3: TextView = view.findViewById(R.id.ftype)
        val textView4: TextView = view.findViewById(R.id.faddress)
        val textView5: TextView = view.findViewById(R.id.fprice_range)
        val textView6: TextView = view.findViewById(R.id.fdistance)
        val imageView : ImageView = view.findViewById(R.id.fresimage)
        val cardView : CardView = view.findViewById(R.id.fratingBackground)
        val toggleButton : ToggleButton = view.findViewById(R.id.toggle1)

        init {
            itemView.setOnClickListener(this)
            toggleButton.setOnClickListener {
                onClickListener.delFav(arrayList[adapterPosition].id)
            }
        }

        override fun onClick(p0: View?) {
            onClickListener.onSiteClick(arrayList[adapterPosition].id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.favrecyclerview_item, parent, false)
        return ViewHolder(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrayList = arrayList[position]

        if (arrayList!=null) {
            holder.textView1.text = arrayList.name
            val rating = arrayList.overallRating
            holder.textView4.text = arrayList.landmark
            holder.textView3.text=arrayList.placeType[0].name
            val cost = arrayList.cost
            val priceRange= PriceRange().getRupeeIcon(cost)
            holder.textView5.text = priceRange
            //holder.textView6.text = String.format("%.1f km", arrayList.distance)

            holder.textView2.text = String.format("%.1f", rating)

            val ratingBackground = RatingBackground().getRatingColor(rating)
            holder.cardView.background.setTint(ratingBackground)
            Glide.with(holder.imageView).load(arrayList.image).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(holder.imageView)
        }
        /*else{
            Toast.makeText(mycontaxt, "Item is null", Toast.LENGTH_LONG).show()
        }*/
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    interface OnFavItemClickListener {
        fun onSiteClick(PlaceId: Long)
        fun delFav(PlaceId: Long)
    }

}