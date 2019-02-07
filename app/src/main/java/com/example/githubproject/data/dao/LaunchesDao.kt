package com.example.githubproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubproject.data.model.Launches

@Dao
interface LaunchesDao {

    @Query("SELECT * FROM Launches")
    fun getAllTask(): LiveData<List<Launches>>

    @Query("SELECT * FROM Launches where `flightNumber` LIKE  :value ")
    fun findByName(value: Int): LiveData<Launches>

    @Query("SELECT * FROM Launches where `flightNumber` LIKE  :value ")
    fun findByLaunches(value: Int): List<Launches>


    @Query("UPDATE Launches SET  picture=:value where flightNumber=:id")
    fun insertImage(value: ByteArray,id:Int)


    @Insert
    fun insert(launches: Launches) : Long

    @Delete
    fun delete(launches: Launches)

    @Update
    fun update(launches: Launches)
}