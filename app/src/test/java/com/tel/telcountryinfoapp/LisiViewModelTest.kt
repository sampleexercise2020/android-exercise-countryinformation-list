package com.tel.telcountryinfoapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tel.telcountryinfoapp.model.CountryInformation
import com.tel.telcountryinfoapp.model.CountryInformationData
import com.tel.telcountryinfoapp.viewmodel.ListViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class LisiViewModelTest {


    @InjectMocks
    var listViewModel = ListViewModel()

    private val testSingle: Single<List<CountryInformationData>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    //When response of getcountryinfo is success
    @Test
    fun getCountriesSuccess(){
        val CountryInformation = CountryInformation("countryname", null)


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