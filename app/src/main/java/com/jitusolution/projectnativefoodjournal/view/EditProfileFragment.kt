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
import com.jitusolution.projectnativefoodjournal.R
import com.jitusolution.projectnativefoodjournal.databinding.FragmentEditProfileBinding
import com.jitusolution.projectnativefoodjournal.model.User
import com.jitusolution.projectnativefoodjournal.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*


class EditProfileFragment : Fragment() , UpdateProfileClickListener{
    private lateinit var viewModel:UserViewModel
    private lateinit var dataBinding:FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //bottomNav.findViewById<>()
        dataBinding= DataBindingUtil.inflate<FragmentEditProfileBinding>(inflater,R.layout.fragment_edit_profile,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(UserViewModel::class.java)
        val name= EditProfileFragmentArgs.fromBundle(requireArguments()).name
        val uuid =1
        viewModel.fetch(name)
        dataBinding.listener=this
        observeViewModel()
    }
    fun observeViewModel()
    {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            dataBinding.user = it

        })
    }

    override fun onUpdateProfileClick(v: View, obj: User) {
        Log.d("cobacek",obj.toString())
        viewModel.update(obj.name,obj.age,obj.weight,obj.height,obj.uuid)
        Toast.makeText(v.context, "User updated", Toast.LENGTH_SHORT).show()
    }


}