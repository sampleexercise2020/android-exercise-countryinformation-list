package com.tel.telcountryinfoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tel.telcountryinfoapp.R
import com.tel.telcountryinfoapp.model.CountryInformationData
import com.tel.telcountryinfoapp.util.getProgressDrawable
import com.tel.telcountryinfoapp.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

/**
 * Author:    Veena
 * Created:   18.04.2020
 *
 * Adapter class converting model information and display to view
 **/

//Inherits RecycleView Adapter
class CountryListAdapter(val countrysList: ArrayList<CountryInformationData>) : RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountryList(newCountrysList:List<CountryInformationData>){
        countrysList.clear()
        countrysList.addAll(newCountrysList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    //Returns size of Row List
    override fun getItemCount()= countrysList.size

    //Binding View to CountryViewHolder based on element of list index
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        println(countrysList)
        holder.view.title.text = countrysList[position].countryInfoTitle
        holder.view.desc.text = countrysList[position].countryInfoDesc

        holder.view.imageView.loadImage(countrysList[position].imageUrl, getProgressDrawable(holder.view.imageView.context))
    }

    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

}
