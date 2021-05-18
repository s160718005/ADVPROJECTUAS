package com.jitusolution.projectnativefoodjournal.util

import android.content.Context
import androidx.room.Room
import com.jitusolution.projectnativefoodjournal.model.FoodJournalDatabase


val DB_NAME="foodjournaldb"

fun buildDB(context: Context): FoodJournalDatabase {
    val db = Room.databaseBuilder(context,
        FoodJournalDatabase::class.java, DB_NAME)
        .build()
    return db
}
