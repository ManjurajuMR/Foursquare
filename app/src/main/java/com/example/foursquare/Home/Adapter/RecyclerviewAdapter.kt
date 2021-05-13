package com.example.foursquare.Home.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foursquare.Home.Adapter.tools.PriceRange
import com.example.foursquare.Home.Adapter.tools.RatingBackground
import com.example.foursquare.R
import com.example.foursquare.model.Datum

class RecyclerviewAdapter(private var arrayList: List<Datum>,private val mycontaxt:Context,private val listener: RecyclerviewAdapter.OnItemClickListener): RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.homerecyclerview_item, parent, false)
            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val arrayList = arrayList[position]
            if (arrayList!=null) {
                holder.restname.text = arrayList.place.name
                Glide.with(mycontaxt).load(arrayList.place.image).placeholder(R.drawable.loading)
                    .into(holder.restimage)
                val rating = arrayList.place.overallRating
                holder.address.text = arrayList.place.landmark
                holder.resType.text=arrayList.place.placeType[0].name
                val cost=arrayList.place.cost
                val priceRange=PriceRange().getRupeeIcon(cost)
                holder.priceRange.text=priceRange
                holder.distance.text=String.format("%.1f km", arrayList.distance)
                holder.rating.text = arrayList.place.overallRating.toString()
                val ratingBackground = RatingBackground().getRatingColor(rating)
                holder.ratingBackground.background.setTint(ratingBackground)
            }else{
                Toast.makeText(mycontaxt, "Item is null", Toast.LENGTH_LONG).show()
            }

         /*   holder.itemView.setOnClickListener {
               // arrayList.place.id
                Toast.makeText(mycontaxt, "Item is clicked ${arrayList.place.id}", Toast.LENGTH_LONG).show()
            }*/
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view),View.OnClickListener{
            val restimage: ImageView = view.findViewById(R.id.restimage)
            val restname: TextView = view.findViewById(R.id.restname)
            val rating: TextView = view.findViewById(R.id.rating)
            val address: TextView =view.findViewById(R.id.address)
            val ratingBackground: CardView =view.findViewById(R.id.ratingBackground)
            val resType: TextView =view.findViewById(R.id.type)
            val priceRange: TextView =view.findViewById(R.id.price_range)
            val distance: TextView =view.findViewById(R.id.distance)


            init {
                itemView.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                val position=adapterPosition
                if (position!=RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }
            }


            /*     init {
                     fav_button.setOnClickListener {
                         MainActivity.myweatherdatabase?.myDao()?.delete(arrayList[adapterPosition].PlaceName)
                         //arrayList.remove(arrayList[adapterPosition])
                         // notifyDataSetChanged()
                         view.findNavController().navigate(R.id.nav_favourite)
                     }
                 }*/
        }
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}
