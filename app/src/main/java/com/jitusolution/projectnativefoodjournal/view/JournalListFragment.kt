package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_create_log.*
import kotlinx.android.synthetic.main.fragment_journal_list.*


class JournalListFragment : Fragment() {
   // private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_journal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       // viewModel= ViewModelProvider(this).get(UserViewModel::class.java)

        super.onViewCreated(view, savedInstanceState)
//        bottomNav.visibility=View.VISIBLE
//        navView.visibility=View.VISIBLE
//    viewModel.getTarget()
//        observeViewModel()

    }
//    fun observeViewModel(){
//        viewModel.bmrLD.observe(viewLifecycleOwner, Observer {
//            txtCoba.text =it.toString()
//
//        })
//
//    }



}