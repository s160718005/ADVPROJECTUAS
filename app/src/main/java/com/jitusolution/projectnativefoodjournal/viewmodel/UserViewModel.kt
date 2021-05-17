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
    fun fetch(uuid:Int){
        launch{
            val db = buildDB(getApplication())
            userLD.value=db.foodjournalDao().selectUser(uuid)
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
    fun update(name:String,age:Int,weight:Double, height:Double,uuid: Int) {
        launch {
            val db = buildDB(getApplication())
            db.foodjournalDao().update(name, age, weight,height, uuid)
        }
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

}