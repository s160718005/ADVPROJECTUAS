package com.jitusolution.projectnativefoodjournal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.model.User
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_create_user.*


class CreateUserFragment : Fragment() {

    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        btnStart.setOnClickListener {
            var radioGender= view.findViewById<RadioButton>(radioGroupGender.checkedRadioButtonId)
            var radioGoal = view.findViewById<RadioButton>(radioGroupGoal.checkedRadioButtonId)
            var user = User(txtName.text.toString(), txtAge.text.toString().toInt(),radioGender.tag.toString(),txtWeight.text.toString().toDouble(),txtHeight.text.toString().toDouble(),radioGoal.tag.toString())
            //pemanggilan add to do nya
            viewModel.addUser(user)
            Toast.makeText(it.context,"User Created", Toast.LENGTH_SHORT).show()
            val action = CreateUserFragmentDirections.actionItemProfile(txtName.text.toString())
           Navigation.findNavController(it).navigate(action)

            //untuk destroy fragment yang terbuka ini mencari benda lain dalam backstack
        }
    }

    }

