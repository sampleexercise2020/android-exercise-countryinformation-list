package com.tel.telcountryinfoapp

import com.tel.telcountryinfoapp.di.ApiModule
import com.tel.telcountryinfoapp.model.CountriesApiService

/**
 * Author:    Veena
 * Created:   28.04.2020
 *
 * Mock class for ApiModule used for testing
 **/

class ApiModuleTest(val mockService: CountriesApiService): ApiModule() {

    override fun provideCountriesApiService(): CountriesApiService {
        return mockService
    }
}
