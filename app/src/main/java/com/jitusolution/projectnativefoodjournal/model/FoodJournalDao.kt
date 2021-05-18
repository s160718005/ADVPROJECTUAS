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
    @Query("select * from user where name= :name")
    suspend fun selectUser(name:String):User
    //untuk update
    @Query("update user set name=:name , age=:age, height=:height,weight=:weight where uuid=:uuid")
    suspend fun update(name:String,age:Int,weight:Double, height:Double,uuid: Int)
//    @Query("Select * from todo where is_done=0 ORDER BY priority DESC")
//    suspend fun selectAllTodoIsDone():List<Todo>
//    @Query("update todo set is_done=1 where uuid= :uuid")
//    suspend fun updateIsDone(uuid: Int)
//
//
//    @Delete
//    suspend fun deleteTodo(todo:Todo)
}