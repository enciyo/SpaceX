package com.example.githubproject.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubproject.data.model.Launches
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface LaunchesDao {

    @Query("SELECT * FROM Launches ORDER BY `flightNumber`")
    fun getAllLaunches(): Observable<List<Launches>>

    @Query("SELECT COUNT(*) FROM Launches")
    fun getSize() : Int

    @Query("SELECT * FROM Launches where `flightNumber` LIKE  :value ")
    fun findByName(value: Int): Observable<Launches>

    @Query("SELECT COUNT(flightNumber) FROM Launches WHERE `flightNumber`= :value")
    fun findByLaunches(value: Int): Boolean

    @Query("SELECT COUNT(bitmap) FROM launches ")
    fun findBitmapLaunches() : Int

    @Query("UPDATE Launches SET  bitmap=:value where flightNumber=:id")
    fun insertImage(value: ByteArray,id:Int)


    @Insert
    fun insert(launches: Launches) : Long

    @Insert
    fun insertAll(launches: List<Launches>)

    @Delete
    fun delete(launches: Launches)

    @Update
    fun update(launches: Launches)
}