package com.jitusolution.projectnativefoodjournal.util

import android.content.Context
import androidx.room.Room


//val DB_NAME="tododb"
//
//fun buildDB(context: Context):FoodJournalDatabase{
//    val db = Room.databaseBuilder(context,
//        FoodJournalDatabase::class.java, DB_NAME)
//        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
//        .build()
//    return db
//}
fun hitungBMR(weight:Double, height:Double, age:Double,sex:String):Double
{
    var bmr:Double = 0.0;
    /*
    For
    BMR = 13,397W + 4,799H - 5,677A + 88,362
    For Women:
    BMR = 9,247W + 3,098H - 4,330A + 447,593
*/
    if(sex == "men")
    {
        bmr = (13.397 * weight)+ (4.799 * height)-(5.677 * age)+ 88.362
    }
    else
    {
        bmr = (9.247 * weight)+ (3.098 * height)-(4.330 * age)+ 447.593
    }
    return bmr
}
fun caloriesTarget(bmr:Double, type:String):Double
{
    var target:Double = 0.0;
    if (type == "maintain")
    {
        target = bmr
    }
    else if(type == "gain")
    {
        target = bmr+ (bmr*0.15)
    }
    else if(type == "loss")
    {
        target = bmr - (bmr*0.15)
    }
    return Math.ceil(target)
}