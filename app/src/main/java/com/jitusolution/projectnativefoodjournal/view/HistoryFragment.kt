package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FragmentHistoryBinding
import com.jitusolution.projectnativefoodjournal.databinding.FragmentJournalListBinding
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel


class HistoryFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding: FragmentHistoryBinding

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
    }


}