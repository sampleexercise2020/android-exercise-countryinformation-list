package com.tel.telcountryinfoapp.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountrysApi {

    @GET("s/2iodh4vg0eortkl/facts.json")
   // fun getCountrys(): Single<List<CountryInformation>>
    fun getCountrys(): Single<List<CountryInformation>>
}