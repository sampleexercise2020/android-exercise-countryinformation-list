package com.tel.telcountryinfoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tel.telcountryinfoapp.model.CountryInformation
import com.tel.telcountryinfoapp.model.CountryInformationData
import com.tel.telcountryinfoapp.model.CountriesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Author:    Veena
 * Created:   18.04.2020
 *
 * Adapter class converting model information and display to view
 **/

class ListViewModel: ViewModel() {

    private val countrysService = CountriesApiService()
    private val disposable = CompositeDisposable()

    val countrys = MutableLiveData<CountryInformation>()
    val countrysData = MutableLiveData<List<CountryInformationData>>()
    val countrysLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            countrysService.getCountrys()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<CountryInformation>(){
                    override fun onSuccess(countryList: CountryInformation) {
                        countrys.value = countryList
                     //   Log.d("countrys.value", countryList.toString() )
                        countrysData.value =  countryList.countryRows
                        countrysLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        countrysLoadError.value = false
                        loading.value = false
                        e.printStackTrace()
                    }


                })
        )
    }

    override fun onCleared(){
        super.onCleared()
        disposable.clear()
    }

}