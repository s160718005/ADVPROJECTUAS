package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FragmentEditProfileBinding
import com.jitusolution.projectnativefoodjournal.databinding.FragmentJournalListBinding
import com.jitusolution.projectnativefoodjournal.viewmodel.DayViewModel
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_create_log.*
import kotlinx.android.synthetic.main.fragment_journal_list.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class JournalListFragment : Fragment(),FabLogClickListener {
   // private lateinit var viewModel: UserViewModel
    private lateinit var viewModel:UserViewModel
    private lateinit var viewModelDay: DayViewModel

    private lateinit var dataBinding: FragmentJournalListBinding
    private var foodLogListAdapter:FoodLogListAdapter = FoodLogListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //return inflater.inflate(R.layout.fragment_journal_list, container, false)
       dataBinding= DataBindingUtil.inflate<FragmentJournalListBinding>(inflater,R.layout.fragment_journal_list,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelDay= ViewModelProvider(this).get(DayViewModel::class.java)

        viewModel.fetch()
        viewModelDay.fetch()

        dataBinding.fablistener=this
        recFoodLogList.layoutManager=LinearLayoutManager(context)
        recFoodLogList.adapter=foodLogListAdapter
//    viewModel.getTarget()
     observeViewModel()

    }
    fun observeViewModel(){
        viewModelDay.totalCalLD.observe(viewLifecycleOwner, Observer {
            dataBinding.total=it
        })
        viewModelDay.statusLD.observe(viewLifecycleOwner, Observer {
            dataBinding.status=it
        })
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user=it
        })
        viewModelDay.dayLD.observe(viewLifecycleOwner, Observer {
            foodLogListAdapter.updateFoodLogList(it)
        })

    }

    override fun onFabLogClick(v: View) {
        findNavController().navigate(R.id.createLogFragment)
    }


}