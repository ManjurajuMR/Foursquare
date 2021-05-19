package com.example.foursquare.Home.Adapter

import android.app.Application
import android.content.Context
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
import com.example.foursquare.Home.Adapter.tools.PriceRange
import com.example.foursquare.Home.Adapter.tools.RatingBackground
import com.example.foursquare.R
import com.example.foursquare.authentication.Constents
import com.example.foursquare.model.Datum
import com.example.foursquare.model.Datum1
import com.example.foursquare.model.FavPdata
import com.example.foursquare.viewmodel.AddFavouriteViewModel
import com.example.foursquare.viewmodel.FavouritesViewModel

class PopularPlaceAdapter(private var arrayList: List<Datum1>, private val mycontext: Context,private val listener: OnItemClickListener): RecyclerView.Adapter<PopularPlaceAdapter.ViewHolder>() {
    private val favouriteViewModel = ViewModelProvider.AndroidViewModelFactory(mycontext.applicationContext as Application).create(
        FavouritesViewModel::class.java)
    private val addfavViewModel = ViewModelProvider.AndroidViewModelFactory(mycontext.applicationContext as Application).create(
        AddFavouriteViewModel::class.java)
    var favouriteData : List<FavPdata>?=null
    val sharedPreferences = mycontext.getSharedPreferences(Constents.Shared_pref, AppCompatActivity.MODE_PRIVATE)
    val userId=sharedPreferences.getString(Constents.USER_ID,"")!!
    val token=sharedPreferences.getString(Constents.USER_TOKEN,"")
    //val placeID=sharedPreferences.getInt(Constents.PLACE_ID,1)
    init {
        if(sharedPreferences.contains(Constents.USER_ID)) {
            getFavourite()
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.homerecyclerview_item, parent, false)
            return ViewHolder(itemView, listener)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val arrayList = arrayList[position]
            if (arrayList!=null) {
                val isFavourite = checkIsFavourite(arrayList.id)
                if(isFavourite)
                    holder.favourite.isChecked = true

                holder.restname.text = arrayList.name
                Glide.with(mycontext).load(arrayList.image).placeholder(R.drawable.loading).into(holder.restimage)
                val rating = arrayList.overallRating
                holder.address.text = arrayList.landmark
                holder.resType.text=arrayList.placeType[0].name
                val cost=arrayList.cost
                val priceRange= PriceRange().getRupeeIcon(cost)
                holder.priceRange.text=priceRange
               // holder.distance.text=String.format("%.1f km", arrayList.distance)
                holder.rating.text = String.format("%.1f",rating)
                val ratingBackground = RatingBackground().getRatingColor(rating)
                holder.ratingBackground.background.setTint(ratingBackground)
            }else{
                Toast.makeText(mycontext, "Item is null", Toast.LENGTH_LONG).show()
            }
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        inner class ViewHolder(view : View, private val onclicklistener: OnItemClickListener) : RecyclerView.ViewHolder(view),View.OnClickListener{
            val restimage: ImageView = view.findViewById(R.id.restimage)
            val restname: TextView = view.findViewById(R.id.restname)
            val rating: TextView = view.findViewById(R.id.rating)
            val address: TextView =view.findViewById(R.id.address)
            val ratingBackground: CardView =view.findViewById(R.id.ratingBackground)
            val resType: TextView =view.findViewById(R.id.type)
            val priceRange: TextView =view.findViewById(R.id.price_range)
            val distance: TextView =view.findViewById(R.id.distance)
            val favourite : ToggleButton = view.findViewById(R.id.toggle1)

            init {
                itemView.setOnClickListener(this)
                favourite.setOnClickListener {
                    if (favourite.isChecked){
                        addFavourites(arrayList[adapterPosition].id)
                    }else{
                        val id=arrayList[adapterPosition].id
                        deleteFavourite(id.toInt())
                    }
                }
            }
            override fun onClick(v: View?) {
                /*val position=adapterPosition
                if (position!=RecyclerView.NO_POSITION) {
                    listener.onItemClick(position)
                }*/
                onclicklistener.onItemClick(arrayList[adapterPosition].id)
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
        fun onItemClick(placeId:Long)
    }

    //
    private fun getFavourite() {

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

    private fun addFavourites(placeId: Long) {
        //val userId = 126

        // val placeId =23
        //Log.d("placeid", "${placeId}")
        //var Token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYW5pc2gxMUBnbWFpbC5jb20iLCJleHAiOjE2MjEzMjk3NzksImlhdCI6MTYyMTMxMTc3OX0.xo_pnRwYeyio35ttJomKzwuH9yIbo33mIXRhTglDeEbTnKJbmLQvqXMB_R5qqRWVMtmRqw3WqHCJGOA3W-abhA"

        if (token != null && placeId != null) {
            val newtoken = "Bearer $token"
            val placeId1 = placeId.toInt().toString()

            val user = hashMapOf(
                "userId" to userId,
                "placeId" to placeId1
            )
            val owner = (mycontext as AppCompatActivity)
            addfavViewModel.addfav(newtoken, user).observe(owner, {
                if (it != null) {
                    if (it.status.toInt() == 200) {
                        Toast.makeText(mycontext, "Added to favourites", Toast.LENGTH_LONG).show()
                        //onBackPressed()
                    } else {
                        Toast.makeText(mycontext, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

        //Toast.makeText(applicationContext, "$tkn", Toast.LENGTH_LONG).show()

    }

    private fun deleteFavourite(id: Int?) {
        val placeId = id
        val newToken = "Bearer $token"

        val userFavourite = hashMapOf("userId" to userId, "placeId" to placeId.toString())

        if (placeId != null) {
            val owner = (mycontext as AppCompatActivity)
            favouriteViewModel.deleteFavourite(newToken, userFavourite).observe(owner) {
                if(it!=null) {
                    if (it.getStatus() == 200) {
                        // startActivity(Intent(this,FavouriteActivity::class.java))
                        Toast.makeText(mycontext, it.getMessage(), Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(mycontext, it.getMessage(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}

