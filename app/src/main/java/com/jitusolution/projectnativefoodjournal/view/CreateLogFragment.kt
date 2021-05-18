package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jitusolution.projectnativefoodjournal.R
import kotlinx.android.synthetic.main.fragment_create_log.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class CreateLogFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_log, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val date = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        txtDate.text = formatter.format(date)
        //var cal =
        txtCal.text
    }

}