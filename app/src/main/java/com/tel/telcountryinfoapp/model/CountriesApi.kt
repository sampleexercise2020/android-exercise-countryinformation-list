package com.tel.telcountryinfoapp.model

import io.reactivex.Single
import retrofit2.http.GET


/**
 * Author:    Veena
 * Created:   18.04.2020
 *
 * Interface for HTTP API Retrofit Library.
 **/

interface CountriesApi {

    //Get JSON request to fetch country information
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getCountrys(): Single<CountryInformation>
}