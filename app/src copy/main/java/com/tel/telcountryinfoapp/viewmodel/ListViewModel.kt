package com.tel.telcountryinfoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tel.telcountryinfoapp.model.CountryInformation
import com.tel.telcountryinfoapp.model.CountrysApiService
import com.tel.telcountryinfoapp.view.CountrysListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private val countrysService = CountrysApiService()
    private val disposable = CompositeDisposable()

    val countrys = MutableLiveData<List<CountryInformation>>()
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
                .subscribeWith(object: DisposableSingleObserver<List<CountryInformation>>(){
                    override fun onSuccess(countryList: List<CountryInformation>) {
                        countrys.value = countryList
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