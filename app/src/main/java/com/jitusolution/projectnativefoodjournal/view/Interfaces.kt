package com.jitusolution.projectnativefoodjournal.view

import android.view.View
import com.jitusolution.projectnativefoodjournal.model.User

interface UpdateProfileClickListener{
    fun onUpdateProfileClick(v: View,obj:User)
}