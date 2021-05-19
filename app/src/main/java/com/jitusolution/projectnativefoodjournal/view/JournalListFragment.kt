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
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FragmentEditProfileBinding
import com.jitusolution.projectnativefoodjournal.databinding.FragmentJournalListBinding
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_create_log.*
import kotlinx.android.synthetic.main.fragment_journal_list.*


class JournalListFragment : Fragment() {
   // private lateinit var viewModel: UserViewModel
    private lateinit var viewModel:UserViewModel
    private lateinit var dataBinding: FragmentJournalListBinding
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
        viewModel.fetch()

//    viewModel.getTarget()
     observeViewModel()

    }
    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            txtAgeJournal.text =it.age.toString() +" Years Old"
            txtNameJournal.text=it.name.toString()
            txtGenderJournal.text=it.gender.toString()
            txtTargetCalorie.text=it.target.toString() +" Cal"
            progressBar.max = it.target.toInt()
            progressBar.progress= it.target.toInt()


        })

    }



}