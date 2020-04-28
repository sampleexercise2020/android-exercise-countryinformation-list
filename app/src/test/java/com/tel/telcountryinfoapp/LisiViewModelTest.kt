package com.tel.telcountryinfoapp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tel.telcountryinfoapp.di.DaggerViewModelComponent
import com.tel.telcountryinfoapp.model.CountriesApiService
import com.tel.telcountryinfoapp.model.CountryInformation
import com.tel.telcountryinfoapp.model.CountryInformationData
import com.tel.telcountryinfoapp.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

/**
 * Author:    Veena
 * Created:   28.04.2020
 *
 * Test service success and failure condition
 **/

class LisiViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var countryService: CountriesApiService

    val application = Mockito.mock(Application::class.java)

    val listViewModel = ListViewModel()

    @Before
    fun setup(){

        MockitoAnnotations.initMocks(this)

        val testComponent = DaggerViewModelComponent.builder()
            .apiModule(ApiModuleTest(countryService))
            .build()
            .inject(listViewModel)

    }

    @Test
    fun getCountriesSuccess(){
        val countriesTestInfo = CountryInformationData(countryInfoDesc = "Beavers can measure 1.3 mt long",countryInfoTitle = "Beavers",imageUrl = null)
        val countriesTestInfoList = listOf<CountryInformationData>()

        val countriesDataTestInfo = CountryInformation(countryTitle = "TestCountries",countryRows=ArrayList<CountryInformationData>())

        countriesDataTestInfo.countryRows?.add(0,countriesTestInfo)
        val testSingle = Single.just(countriesDataTestInfo)

        Mockito.`when`(countryService.getCountrys()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(1,listViewModel.countrys.value?.countryRows?.size)
        Assert.assertEquals(false,listViewModel.countrysLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)


    }

    @Test
    fun getCountriesFailure() {
        val testSingle = Single.error<CountryInformation>(Throwable())
        Mockito.`when`(countryService.getCountrys()).thenReturn(testSingle)

        listViewModel.refresh()

        Assert.assertEquals(null,listViewModel.countrys.value)
        Assert.assertEquals(false,listViewModel.countrysLoadError.value)
        Assert.assertEquals(false,listViewModel.loading.value)

    }



    //Creates New Thread and Main Thread and returns immediately when call is being made
    @Before
    fun setupRxSchedulers(){
        val immediate = object: Scheduler(){
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() },true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

}