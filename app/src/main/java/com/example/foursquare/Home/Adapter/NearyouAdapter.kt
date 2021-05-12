package com.example.foursquare.Home.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.RecyclerView
import com.example.foursquare.R

class NearyouAdapter(/*private var arrayList : List<>,*/ private val clickListener: OnNearyouItemClickListener) : RecyclerView.Adapter<NearyouAdapter.ViewHolder>() {

    inner class ViewHolder(view : View, private val onClickListener: OnNearyouItemClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val textView1: TextView = view.findViewById(R.id.restname)
        val textView2: TextView = view.findViewById(R.id.rating)
        val textView3: TextView = view.findViewById(R.id.place)
        val textView4: TextView = view.findViewById(R.id.address)
        val imageView1 : ImageView = view.findViewById(R.id.restimage)

        override fun onClick(p0: View?) {
            onClickListener.onNearyouClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.homerecyclerview_item, parent, false)
        return ViewHolder(itemView,clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val arrayList = arrayList[position]

    }

    override fun getItemCount(): Int {
        val ab : Int = 10
        return ab
        //return arrayList.size
    }

    interface OnNearyouItemClickListener {
        fun onNearyouClick( position: Int)
    }

}