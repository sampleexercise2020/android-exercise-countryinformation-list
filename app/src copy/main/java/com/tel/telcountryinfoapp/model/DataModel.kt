package com.tel.telcountryinfoapp.model

import com.google.gson.annotations.SerializedName

data class CountryInformationData(


    @SerializedName("title")
    val countryTitle:String?,

    @SerializedName("rows")
    val countryRows:ArrayList<CountryInformationData>?

//    @SerializedName("name")
//    val countryTitle:String?,
//
//    @SerializedName("breed_group")
//    val countryDesc:String?,
//
//    @SerializedName("url")
//    val imageUrl:String?
)

data class CountryInformation(

    @SerializedName("title")
    val countryInfoTitle:String?,

    @SerializedName("description")
    val countryInfoDesc:String?,

    @SerializedName("imageHref")
    val imageUrl:String?
)