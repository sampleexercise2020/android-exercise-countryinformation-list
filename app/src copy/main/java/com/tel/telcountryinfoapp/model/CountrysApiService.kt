package com.tel.telcountryinfoapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountrysApiService {

   // private val BASE_URL = "https://raw.githubusercontent.com"

    private val BASE_URL = "https://dl.dropboxusercontent.com"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountrysApi::class.java)

    fun getCountrys(): Single<List<CountryInformation>> {
        return api.getCountrys()
    }

}