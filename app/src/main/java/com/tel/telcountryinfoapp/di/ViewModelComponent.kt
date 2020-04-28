package com.tel.telcountryinfoapp.di

import com.tel.telcountryinfoapp.viewmodel.ListViewModel
import dagger.Component

/**
 * Author:    Veena
 * Created:   26.04.2020
 *
 * Interface class to inject ViewModel
 **/

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}