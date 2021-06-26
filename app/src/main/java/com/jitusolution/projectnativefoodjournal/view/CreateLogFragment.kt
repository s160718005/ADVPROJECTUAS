package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FragmentCreateLogBinding
import com.jitusolution.projectnativefoodjournal.databinding.FragmentCreateUserBinding
import com.jitusolution.projectnativefoodjournal.model.Day
import com.jitusolution.projectnativefoodjournal.model.User
import com.jitusolution.projectnativefoodjournal.util.getDateFormmatted
import com.jitusolution.projectnativefoodjournal.viewmodel.DayViewModel
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.foodlog_item_layout.*
import kotlinx.android.synthetic.main.fragment_create_log.*
import kotlinx.android.synthetic.main.fragment_create_user.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class CreateLogFragment : Fragment(),LogThisMealClickListener {

    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelDay:DayViewModel
    private lateinit var dataBinding: FragmentCreateLogBinding

    var bmr:Int = 0
    var target:Int = 0
    var totalCal:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentCreateLogBinding>(inflater,R.layout.fragment_create_log,container,false)
        return  dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        viewModelDay= ViewModelProvider(this).get(DayViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        dataBinding.logmeallistener = this
        dataBinding.day = Day("","",0,0,0,0,"",target,0)
        viewModel.fetch()
        viewModelDay.fetch()
        observeViewModel()

    }
    fun observeViewModel() {
        viewModelDay.sisaLD.observe(viewLifecycleOwner, Observer {
            dataBinding.sisa = it

        })
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            bmr = it.bmr
            target = it.target

        })


    }
    override fun onLogThisMealClick(v: View,obj:Day) {
        if(obj.namamakanan.isNullOrBlank() || obj.kalorimakanan.toString().isNullOrBlank())
        {
            Toast.makeText(v.context, "Input text tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
        else
        {
            var day = Day(getDateFormmatted(),obj.namamakanan,obj.kalorimakanan.toString().toInt(),0,bmr,target,"",0,0)

            viewModelDay.addDay(day)
            Toast.makeText(v.context, "Makanan sudah tersimpan , Tetap semangat ya!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.itemFood)
        }
    }

}