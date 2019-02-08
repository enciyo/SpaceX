package com.example.githubproject.data.dao

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubproject.data.model.Launches

@Dao
interface LaunchesDao {

    @Query("SELECT * FROM Launches ORDER BY `flightNumber`")
    fun getAllTask(): LiveData<List<Launches>>

    @Query("SELECT * FROM Launches where `flightNumber` LIKE  :value ")
    fun findByName(value: Int): LiveData<Launches>

    @Query("SELECT COUNT(flightNumber) FROM Launches WHERE `flightNumber`= :value")
    fun findByLaunches(value: Int): Boolean

    @Query("SELECT COUNT(bitmap) FROM launches ")
    fun findBitmapLaunches() : Int

    @Query("UPDATE Launches SET  bitmap=:value where flightNumber=:id")
    fun insertImage(value: ByteArray,id:Int)


    @Insert
    fun insert(launches: Launches) : Long

    @Delete
    fun delete(launches: Launches)

    @Update
    fun update(launches: Launches)
}