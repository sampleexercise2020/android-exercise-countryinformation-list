package com.tel.telcountryinfoapp.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.tel.telcountryinfoapp.R
import com.tel.telcountryinfoapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val countrysListAdapter = CountrysListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        countrysList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countrysListAdapter

        }

        refreshLayout.setOnRefreshListener {
            countrysList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.countrys.observe(this, Observer { countrys->
            countrys?.let {
                countrysList.visibility = View.VISIBLE
                countrysListAdapter.updateCountryList(countrys)
            }
        })

        viewModel.countrysLoadError.observe(this, Observer { isError ->
            isError?.let{
                listError.visibility = View.VISIBLE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                listError.visibility = View.GONE
                loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                  // listError.visibility = View.GONE
                    countrysList.visibility = View.GONE
                }
            }
        })
    }

}
