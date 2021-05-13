package com.example.foursquare.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.foursquare.PhotoDetaisActivity
import com.example.foursquare.R
import com.example.foursquare.model.Photos

class PhotosAdapter(
        private var images:Photos,
        var mycontext: Context
    ): BaseAdapter(){
        var layoutinflater=mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun getCount(): Int {
            return images.data.size
        }

        override fun getItem(position: Int): Any {
            return images.data[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view=convertView
            if (view==null){
                view=layoutinflater.inflate(R.layout.photos,parent,false)
            }
            val photo=images.data[position]
            if (photo != null) {
                var imageview:ImageView=view?.findViewById<ImageView>(R.id.photos)!!
                Glide.with(mycontext).load(photo.image).placeholder(R.drawable.loading).into(imageview)

                imageview.setOnClickListener {
                    val intent=Intent(it.context,PhotoDetaisActivity::class.java)
                    intent.putExtra("photoId",photo.photoId)
                    it.context.startActivity(intent)
                    Toast.makeText(mycontext, "Item is clicked ${photo.photoId}", Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(mycontext, "Item is null", Toast.LENGTH_LONG).show()
            }
            /* var imageview= view?.findViewById<ImageView>(R.id.photos)
             imageview?.setImageResource(images[position])
 */
            return view!!
        }
}