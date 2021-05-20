package com.jitusolution.projectnativefoodjournal.model

import androidx.room.*

@Dao
interface FoodJournalDao {
    //tuliskan operasi apa saja yang dibutuhkan untuk table todoo
    //kalau cuma single ga usah varargs tapi di project ini kita buat supaya bisa banyak todoo 1 2 3 4 dan seterusnya parameter nya bisa lebih dari 1
    //kalau primary key carash yang lama di ganti dengan yang baru
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)
    //suspend ini ada kaitannya dengna corotine aitu bisa di pause dan continue
    @Query("Select * from user ORDER BY uuid DESC")
    suspend fun selectAllTodo():List<User>
    //@Query("select * from todoo where uuid= :id AND kolumb = :c")
    @Query("select * from user where uuid = 1")
    suspend fun selectUser():User
    @Query("select count(*) from user")
    suspend fun countUser():Int
    //untuk update
    @Query("update user set name=:name , age=:age, height=:height,weight=:weight,bmr=:bmr,caloriestarget=:target where uuid=:uuid")
    suspend fun update(name:String,age:Int,weight:Double, height:Double,uuid: Int, bmr:Int, target:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(vararg day: Day)

    @Query("select * from day where tanggal=:tanggal")
    suspend fun selectDay(tanggal:String):List<Day>

    @Query("SELECT totalkalori FROM day WHERE tanggal =:tanggal ORDER BY uuidday DESC LIMIT 1 ")
            suspend fun selectTotalCal(tanggal:String):Int
    @Query("SELECT sisacalori FROM day WHERE tanggal =:tanggal ORDER BY uuidday DESC LIMIT 1 ")
    suspend fun selectSisaCal(tanggal:String):Int
    @Query("SELECT status FROM day WHERE tanggal =:tanggal ORDER BY uuidday DESC LIMIT 1 ")
    suspend fun selectStatus(tanggal:String):String

    @Query("update day set totalkalori= (:totalkalori+:kaloriMakanan) where uuidday=:uuid")
    suspend fun updateDaily(totalkalori:Int,kaloriMakanan:Int,uuid:Int)
//    @Query("select caloriestarget from user")
//    suspend fun selectTarget():Double

//    @Query("Select * from todo where is_done=0 ORDER BY priority DESC")
//    suspend fun selectAllTodoIsDone():List<Todo>
//    @Query("update todo set is_done=1 where uuid= :uuid")
//    suspend fun updateIsDone(uuid: Int)
//
//
//    @Delete
//    suspend fun deleteTodo(todo:Todo)
}