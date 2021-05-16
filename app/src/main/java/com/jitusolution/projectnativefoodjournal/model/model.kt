package com.jitusolution.projectnativefoodjournal.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    var name: String,
    var age: String,
    var gender:String,
    var weight:String,
    var height:String,
    var personalgoal:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}