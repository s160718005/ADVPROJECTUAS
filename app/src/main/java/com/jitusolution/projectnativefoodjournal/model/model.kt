package com.jitusolution.projectnativefoodjournal.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    var name: String,
    var age: Int,
    var gender:String,
    var weight:Double,
    var height:Double,
    var personalgoal:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}