package com.jitusolution.projectnativefoodjournal.util

import android.content.Context
import androidx.room.Room
import com.jitusolution.projectnativefoodjournal.model.FoodJournalDatabase
import java.text.SimpleDateFormat
import java.util.*


val DB_NAME="foodjournaldb"

fun buildDB(context: Context): FoodJournalDatabase {
    val db = Room.databaseBuilder(context,
        FoodJournalDatabase::class.java, DB_NAME)
        .build()
    return db
}

fun getDateFormmatted():String{
    var formatter = SimpleDateFormat("d MMM yyyy")
    return formatter.format(Date())
}
fun getDateFormmatted2():String{
    var formatter = SimpleDateFormat("MMM yyyy")
    return formatter.format(Date())
}
fun getDaysInMonth():Int{
    var calendar = Calendar.getInstance()
    var day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    return day
}

