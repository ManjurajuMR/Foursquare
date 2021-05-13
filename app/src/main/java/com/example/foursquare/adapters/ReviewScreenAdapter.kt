package com.example.foursquare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.foursquare.R
import com.example.foursquare.model.Reviews

class ReviewScreenAdapter(private var arrayList : List<Reviews>, private val mycontext: Context/*, private val clickListener: OnCoffeeItemClickListener*/) : RecyclerView.Adapter<ReviewScreenAdapter.ViewHolder>() {

    inner class ViewHolder(view : View/*, private val onClickListener: OnCoffeeItemClickListener*/) : RecyclerView.ViewHolder(view)/*, View.OnClickListener*/{
        val textView1: TextView = view.findViewById(R.id.person_name)
        val textView2: TextView = view.findViewById(R.id.person_review)
        val textView3: TextView = view.findViewById(R.id.review_date)
        val imageView1 : ImageView = view.findViewById(R.id.person_img)

        /*override fun onClick(p0: View?) {
            onClickListener.onCoffeeClick(adapterPosition)
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val arrayList = arrayList[position]
        if (arrayList!=null) {
            holder.textView1.text = arrayList.userName
            holder.textView2.text = arrayList.review
            holder.textView3.text = arrayList.date
            Glide.with(holder.imageView1).load(arrayList.userImage).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(holder.imageView1)
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    /*interface OnCoffeeItemClickListener {
        fun onCoffeeClick( position: Int)
    }*/

}