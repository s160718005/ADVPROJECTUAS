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
    var personalgoal:String,
        @ColumnInfo(name="bmr")
    var bmr:Int,
        @ColumnInfo(name="caloriestarget")
    var target:Int,

){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}
@Entity
data class Day(
        @ColumnInfo(name="tanggal")
        var tanggal: String,
        @ColumnInfo(name="namamakanan")
        var namamakanan: String,
        @ColumnInfo(name="kalorimakanan")
        var kalorimakanan:Int,
        @ColumnInfo(name="totalkalori")
        var totalkalori:Int,
        @ColumnInfo(name="bmr")
        var bmr:Int,
        @ColumnInfo(name="caloriestarget")
        var target:Int,
        @ColumnInfo(name="status")
        var status: String

        ){
    @PrimaryKey(autoGenerate = true)
    var uuidday:Int=0
}