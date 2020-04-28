package com.tel.telcountryinfoapp.model

import com.tel.telcountryinfoapp.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

/**
 * Author:    Veena
 * Created:   18.04.2020
 *
 * This class used to get backend data by using dagger
 **/


class CountriesApiService {

    @Inject
    lateinit var api:CountriesApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCountrys(): Single<CountryInformation> {
        return api.getCountrys()
    }
}