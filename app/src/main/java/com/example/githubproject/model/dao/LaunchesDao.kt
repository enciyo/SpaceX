package com.example.githubproject.model.dao

import androidx.room.*
import com.example.githubproject.model.data.Launches
import io.reactivex.Single

@Dao
interface LaunchesDao {

    @Query("SELECT * FROM Launches")
    fun getAllTask(): List<Launches>

    @Query("SELECT * FROM Launches where `flightNumber` LIKE  :value ")
    fun findByName(value: Int): Single<List<Launches>>

    @Query("UPDATE Launches SET  picture=:value where flightNumber=:id")
    fun insertImage(value: ByteArray,id:Int)


    @Insert
    fun insert(launches: List<Launches>)

    @Delete
    fun delete(launches: Launches)

    @Update
    fun update(launches: Launches)
}