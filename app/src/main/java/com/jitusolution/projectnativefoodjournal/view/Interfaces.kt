package com.jitusolution.projectnativefoodjournal.view

import android.view.View
import com.jitusolution.projectnativefoodjournal.model.User


interface StartJourneyClickListener{
    fun onStartJourneyClick(v: View)
}
interface UpdateProfileClickListener{
    fun onUpdateProfileClick(v: View,obj:User)
}
interface RadioGenderClickListener{
    fun onRadioGenderClick(v: View)
}
interface RadioGoalClickListener{
    fun onRadioGoalClick(v: View)
}
