package com.jitusolution.projectnativefoodjournal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jitusolution.projectnativefoodjournal.model.User
import com.jitusolution.projectnativefoodjournal.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job= Job()
    val userLD = MutableLiveData<User>()
    val targetLD = MutableLiveData<Double>()
    val jumlahLD = MutableLiveData<Int>()

    fun fetch(){
        launch{
            val db = buildDB(getApplication())
            userLD.value=db.foodjournalDao().selectUser()
        }
    }
    fun countUser(){
        launch{
            val db = buildDB(getApplication())
            jumlahLD.value=db.foodjournalDao().countUser()
        }
    }
    fun addUser(user: User)
    {
        //ini single todoo
        launch{
            //apapun yang di dalam launch ini dilakukand dalam thread terpisah tapi masih punya akses dalam ui
            //val db = Room.databaseBuilder(getApplication(),
            //TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            db.foodjournalDao().insertAll(user)

        }
    }
    fun update(name:String,age:Int,weight:Double, height:Double,uuid: Int,bmr:Int, target:Int) {
        launch {
            val db = buildDB(getApplication())
            db.foodjournalDao().update(name, age, weight,height, 1, bmr,target)
        }
    }
//    fun getTarget(){
//        launch {
//            val db = buildDB(getApplication())
//            targetLD.value =db.foodjournalDao().selectTarget()
//        }
//    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun hitungBMR(weight:Double, height:Double, age:Int,sex:String):Int
    {
        var bmr:Int = 0;
        /*
        For
        BMR = 13,397W + 4,799H - 5,677A + 88,362
        For Women:
        BMR = 9,247W + 3,098H - 4,330A + 447,593
    */
        if(sex == "Male")
        {
            bmr = ((13.397 * weight)+ (4.799 * height)-(5.677 * age)+ 88.362).toInt()
        }
        else
        {
            bmr = ((9.247 * weight)+ (3.098 * height)-(4.330 * age)+ 447.593).toInt()
        }
        return bmr
    }
    fun caloriesTarget(bmr:Int, type:String):Int
    {
        var target:Int = 0;
        if (type == "Maintain Weight")
        {
            target = bmr
        }
        else if(type == "Gain Weight")
        {
            target = (Math.ceil(bmr + (bmr*0.15))).toInt()
        }
        else if(type == "Loss Weight")
        {
            target = (Math.ceil(bmr - (bmr*0.15))).toInt()
        }
        return target
    }
}