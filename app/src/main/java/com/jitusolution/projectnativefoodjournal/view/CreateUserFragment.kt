package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FragmentCreateUserBinding
import com.jitusolution.projectnativefoodjournal.model.User
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_create_user.*


class CreateUserFragment : Fragment(),StartJourneyClickListener,RadioGenderClickListener,RadioGoalClickListener {

    private lateinit var viewModel: UserViewModel
    private lateinit var dataBinding:FragmentCreateUserBinding
    var genderUser = "";
    var goal ="";
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_user, container, false)
        dataBinding= DataBindingUtil.inflate<FragmentCreateUserBinding>(inflater,R.layout.fragment_create_user,container,false)
        return  dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.countUser()
        dataBinding.startlistener=this
        dataBinding.radiogenderlistener=this
        dataBinding.radiogoallistener=this
        observeViewModel()

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

    override fun onStartJourneyClick(v: View) {
        if(txtName.text.toString().isNullOrBlank() || txtAge.text.toString().isNullOrBlank() || txtHeight.text.toString().isNullOrBlank() || txtWeight.text.toString().isNullOrBlank() || genderUser.isNullOrBlank() || goal.isNullOrBlank())
        {
            Toast.makeText(v.context, "Input text dan radio button tidak boleh kosong", Toast.LENGTH_SHORT).show()
        }
        else
        {
            //var radioGender = v.findViewById<RadioButton>(radioGroupGender.checkedRadioButtonId)
            //var radioGoal = v.findViewById<RadioButton>(radioGroupGoal.checkedRadioButtonId)
            var bmr: Double = viewModel.hitungBMR(txtWeight.text.toString().toDouble(), txtHeight.text.toString().toDouble(), txtAge.text.toString().toInt(), genderUser)
            var target: Double = viewModel.caloriesTarget(bmr, goal)
            var user = User(txtName.text.toString(), txtAge.text.toString().toInt(), genderUser, txtWeight.text.toString().toDouble(), txtHeight.text.toString().toDouble(), goal, bmr, target)
            viewModel.addUser(user)
            Toast.makeText(v.context, "User Created target = " + target.toString(), Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.itemFood)
        }
    }

    override fun onRadioGenderClick(v: View) {
        genderUser=v.tag.toString();
    }

    override fun onRadioGoalClick(v: View) {
        goal=v.tag.toString();
    }
}

