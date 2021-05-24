package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FragmentHistoryBinding
import com.jitusolution.projectnativefoodjournal.databinding.FragmentJournalListBinding
import com.jitusolution.projectnativefoodjournal.viewmodel.DayViewModel
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_journal_list.*


class HistoryFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelDay: DayViewModel
    private lateinit var dataBinding: FragmentHistoryBinding

    private var reportListAdapter:FoodJournalReportListAdapter = FoodJournalReportListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentHistoryBinding>(inflater,R.layout.fragment_history,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelDay= ViewModelProvider(this).get(DayViewModel::class.java)
        viewModelDay.getReport()

        recReportList.layoutManager= LinearLayoutManager(context)
        recReportList.adapter=reportListAdapter
        observeViewModel()
    }
    fun observeViewModel(){

        viewModelDay.reportLD.observe(viewLifecycleOwner, Observer {
            reportListAdapter.updateReportList(it)
        })

    }


}