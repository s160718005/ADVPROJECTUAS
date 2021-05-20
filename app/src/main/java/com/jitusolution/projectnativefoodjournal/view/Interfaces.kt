package com.jitusolution.projectnativefoodjournal.view

import android.view.View
import com.jitusolution.projectnativefoodjournal.model.Day
import com.jitusolution.projectnativefoodjournal.model.User


interface StartJourneyClickListener{
    fun onStartJourneyClick(v: View,obj:User)
}
interface UpdateProfileClickListener{
    fun onUpdateProfileClick(v: View,obj:User)
}
interface RadioGenderClickListener{
    fun onRadioGenderClick(v: View,obj:User)
}
interface RadioGoalClickListener{
    fun onRadioGoalClick(v: View,obj:User)
}
interface FabLogClickListener{
    fun onFabLogClick(v:View)
}
interface LogThisMealClickListener{
    fun onLogThisMealClick(v:View,obj:Day)
}
