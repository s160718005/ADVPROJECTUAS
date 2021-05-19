package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.model.User
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_create_user.*


class CreateUserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    var jumlah = 0;
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.countUser()
        observeViewModel()

        //txtName.setText(jumlahUser)

        btnStart.setOnClickListener {
            if(txtName.text.toString().isNullOrBlank() || txtAge.text.toString().isNullOrBlank() || txtHeight.text.toString().isNullOrBlank() || txtWeight.text.toString().isNullOrBlank())
            {
                Toast.makeText(it.context, "Input tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var radioGender = view.findViewById<RadioButton>(radioGroupGender.checkedRadioButtonId)
                var radioGoal = view.findViewById<RadioButton>(radioGroupGoal.checkedRadioButtonId)
                var bmr: Double = viewModel.hitungBMR(txtWeight.text.toString().toDouble(), txtHeight.text.toString().toDouble(), txtAge.text.toString().toInt(), radioGender.tag.toString())
                var target: Double = viewModel.caloriesTarget(bmr, radioGoal.tag.toString())
                var user = User(txtName.text.toString(), txtAge.text.toString().toInt(), radioGender.tag.toString(), txtWeight.text.toString().toDouble(), txtHeight.text.toString().toDouble(), radioGoal.tag.toString(), bmr, target)
                //pemanggilan add to do nya
                viewModel.addUser(user)
                Toast.makeText(it.context, "User Created target = " + target.toString(), Toast.LENGTH_SHORT).show()
                //val action = CreateUserFragmentDirections.actionItemFood(txtName.text.toString())
                //Navigation.findNavController(it).navigate(action)
                findNavController().navigate(R.id.itemFood)
            }


            //untuk destroy fragment yang terbuka ini mencari benda lain dalam backstack
        }
    }

    fun observeViewModel() {
        viewModel.jumlahLD.observe(viewLifecycleOwner, Observer {
            if(it >= 1)
            {
                //val action = CreateUserFragmentDirections.actionItemReport()
                findNavController().navigate(R.id.itemFood)
            }

        })

    }
}

