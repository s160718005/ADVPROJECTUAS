package com.jitusolution.projectnativefoodjournal.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jitusolution.projectnativefoodjournal.model.Day
import com.jitusolution.projectnativefoodjournal.model.User
import com.jitusolution.projectnativefoodjournal.util.buildDB
import com.jitusolution.projectnativefoodjournal.util.getDateFormmatted
import com.jitusolution.projectnativefoodjournal.util.getDateFormmatted2
import com.jitusolution.projectnativefoodjournal.util.getDaysInMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import kotlin.coroutines.CoroutineContext

class DayViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job= Job()
    val userLD = MutableLiveData<User>()
val totalCalLD = MutableLiveData<Int>()
    val dayLD = MutableLiveData<List<Day>>()
    val reportLD = MutableLiveData<List<Day>>()
    var sisaLD = MutableLiveData<Int>()
    val statusLD = MutableLiveData<String>()
    fun fetch(){
        launch{
            val db = buildDB(getApplication())
            dayLD.value=db.foodjournalDao().selectDay(getDateFormmatted())
            if(db.foodjournalDao().selectSisaCal(getDateFormmatted())==null)
            {
                sisaLD.value = db.foodjournalDao().selectUser().target

            }
            else
            {
                sisaLD.value=db.foodjournalDao().selectSisaCal(getDateFormmatted())
            }
            if(db.foodjournalDao().selectTotalCal(getDateFormmatted())==null)
            {
                totalCalLD.value = 0;

            }
            else
            {
                totalCalLD.value =db.foodjournalDao().selectTotalCal(getDateFormmatted())
            }
            if(db.foodjournalDao().selectStatus(getDateFormmatted())==null)
            {
                statusLD.value = "LOW";
            }
            else
            {
                statusLD.value =db.foodjournalDao().selectStatus(getDateFormmatted())
            }



        }
    }
    fun getReport()
    {
        launch{
            val db = buildDB(getApplication())
            //reportLD.value=db.foodjournalDao().getReport(getDateFormmatted2())
            val tanggal = db.foodjournalDao().getTanggal('%'+getDateFormmatted2()+'%')
            var listTanggal = arrayListOf<Int>()
            var listReportDay = arrayListOf<Day>()
            Log.d("cek",tanggal.toString())
            for(t in tanggal)
            {
                var convert = SimpleDateFormat("d").parse(t.tanggal)
                listTanggal.add(SimpleDateFormat("d").format(convert).toInt())
            }
            Log.d("ceklist",listTanggal.toString())
            //listTanggal.add(20)
            for(i in 1 .. getDaysInMonth())
            {
                if(listTanggal.contains(i))
                {
                    Log.d("masuk","masuk")
                    var totalKalori= db.foodjournalDao().getTotalKalori(i.toString()+' '+ getDateFormmatted2())
                    var status=db.foodjournalDao().selectStatus(i.toString()+' '+ getDateFormmatted2())
                    var jumlahmakanan = db.foodjournalDao().selectJumlahMakanan(i.toString()+' '+ getDateFormmatted2())
                    listReportDay.add(Day(i.toString()+' '+ getDateFormmatted2(),"",0,totalKalori,0,0,status,0,jumlahmakanan))
                }
                else
                {
                    listReportDay.add(Day(i.toString()+' '+ getDateFormmatted2(),"",0,0,0,0,"LOW",0,0))
                }
            }
            reportLD.value = listReportDay



        }
    }

    fun addDay(day: Day)
    {
        //ini single todoo
        launch{
            //apapun yang di dalam launch ini dilakukand dalam thread terpisah tapi masih punya akses dalam ui
            //val db = Room.databaseBuilder(getApplication(),
            //TodoDatabase::class.java, "tododb").build()
            val db = buildDB(getApplication())
            var total:Int = 0
            var jumlahMakanan:Int = 0

            if (db.foodjournalDao().selectTotalCal(getDateFormmatted())==null)
            {
                total = 0;
                jumlahMakanan = 0

            }
            else{
                total = db.foodjournalDao().selectTotalCal(getDateFormmatted())
                jumlahMakanan = db.foodjournalDao().selectJumlahMakanan((getDateFormmatted()))

            }
            day.totalkalori = total+day.kalorimakanan
            day.jumlahmakanan = jumlahMakanan + 1
            var status = "low"

            if(day.totalkalori<=(0.5*day.target))
            {
                status = "LOW"
            }
            else if(day.totalkalori>(0.5*day.target) && day.totalkalori <= day.target)
            {
                status = "NORMAL"
            }
            else
            {
                status = "EXCEED"
            }
            day.status = status
            day.sisa = day.target - day.totalkalori
            if(day.sisa<0)
            {
                day.sisa =0;
            }
            db.foodjournalDao().insertDay(day)

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