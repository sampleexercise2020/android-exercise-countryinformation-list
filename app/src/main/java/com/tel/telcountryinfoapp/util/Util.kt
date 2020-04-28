package com.tel.telcountryinfoapp.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tel.telcountryinfoapp.R

/**
 * Author:    Veena
 * Created:   18.04.2020
 *
 * Adapter class converting model information and display to view
 **/

fun getProgressDrawable(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply{

        strokeWidth=10f
        centerRadius=50f
        start()
    }
}


fun ImageView.loadImage(uri: String?,progressDrawable:CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(R.drawable.no_image_available)
        .error(R.drawable.no_image_available)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}