package com.example.foursquare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


//class AddReviewPhotoAdapter: RecyclerView.Adapter<AddReviewPhotoAdapter.PhotoViewHolder>() {

//    lateinit var context: Context
//    lateinit var photoList: List<Uri>
//
//    fun AddReviewPhotoAdapter(context: Context){
//        this.context=context
//    }
//
//    fun setData(list: List<Uri>){
//        this.photoList = list
//        notifyDataSetChanged()
//    }
//
//    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        lateinit var Photos: ImageView
//
//        fun PhotoViewHolder(itemView : View){
//
//            Photos = itemView.findViewById(R.id.photos)
//
//
//        }
//
//    }
//
//    override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): AddReviewPhotoAdapter.PhotoViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos, parent, false)
//        return PhotoViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: AddReviewPhotoAdapter.PhotoViewHolder, position: Int) {
//        var uri = photoList.get(position)
//        try {
//            var bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver,uri)
//            holder.Photos.setImageBitmap(bitmap)
//        } catch (e: IOException){
//            e.printStackTrace()
//        }
//    }
//
//    override fun getItemCount(): Int {
//        if (photoList == null){
//            return 0
//        }
//        else{
//            return photoList.size
//        }
//    }
//
////    interface OnPhotoItemClickListener {
////        fun onPhotoClick( position: Int)
////    }

class AddReviewPhotoAdapter(private val context: AddReviewActivity, private val chaptersList: ArrayList<String>) :
        RecyclerView.Adapter<AddReviewPhotoAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return chaptersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.chapterName?.text = chaptersList.get(position)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, chaptersList.get(position), Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var chapterName = view.findViewById<TextView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AddReviewPhotoAdapter.ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.photos, parent, false)
        )
    }
}