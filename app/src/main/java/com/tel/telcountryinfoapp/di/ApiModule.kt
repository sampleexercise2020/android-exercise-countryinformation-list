package com.tel.telcountryinfoapp.di

import com.tel.telcountryinfoapp.model.CountriesApi
import com.tel.telcountryinfoapp.model.CountriesApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author:    Veena
 * Created:   26.04.2020
 *
 * Create Retrofit Builder
 **/

@Module
open class ApiModule {

    private val BASEURL = "https://dl.dropboxusercontent.com"

    @Provides
     fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
   open fun provideCountriesApiService(): CountriesApiService{
        return CountriesApiService()
    }
}