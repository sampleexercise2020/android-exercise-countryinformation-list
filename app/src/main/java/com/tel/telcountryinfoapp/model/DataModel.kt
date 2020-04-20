package com.tel.telcountryinfoapp.model

import com.google.gson.annotations.SerializedName

/**
 * Author:    Veena
 * Created:   18.04.2020
 *
 * Data Model containing informations about JSON Data
 **/


// This class contains information about JSON Object with
data class CountryInformation(

    @SerializedName("title")
    val countryTitle:String?,

    @SerializedName("rows")
    val countryRows:ArrayList<CountryInformationData>?

)

// This class contains information about JSON Array with rows information
data class CountryInformationData(

    @SerializedName("title")
    val countryInfoTitle:String?,

    @SerializedName("description")
    val countryInfoDesc:String?,

    @SerializedName("imageHref")
    val imageUrl:String?
)