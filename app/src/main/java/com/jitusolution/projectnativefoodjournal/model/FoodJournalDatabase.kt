package com.jitusolution.projectnativefoodjournal.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class), version = 1)
abstract class FoodJournalDatabase: RoomDatabase() {
    //isinya semua dao yang kalian punya ini kebutulan cuma 1
    abstract fun userDao():FoodJournalDao
    companion object{
        @Volatile private var instance: FoodJournalDatabase ?= null
        private  val LOCK = Any()
        //lock ini di sinkronisasi dengan synchornized
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FoodJournalDatabase::class.java,
            "foodjournaldb"
        )
            //bisa ditambahkan koma koma kalau migrationnya banyak
            .build()
        //kalau ngga null dijalankan
        //kalau instance null build database
        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also{
                instance = it
            }

        }


    }
}