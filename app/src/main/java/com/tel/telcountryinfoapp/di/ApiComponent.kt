package com.tel.telcountryinfoapp.di

import com.tel.telcountryinfoapp.model.CountriesApiService
import dagger.Component

/**
 * Author:    Veena
 * Created:   26.04.2020
 *
 * Interface class to inject Service
 **/

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesApiService)
}

