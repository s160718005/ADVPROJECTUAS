package com.jitusolution.projectnativefoodjournal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
        @ColumnInfo(name="name")
    var name: String,
        @ColumnInfo(name="age")
    var age: Int,
        @ColumnInfo(name="gender")
    var gender:String,
        @ColumnInfo(name="weight")
    var weight:Double,
        @ColumnInfo(name="height")
    var height:Double,
        @ColumnInfo(name="personalgoal")
    var personalgoal:String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}