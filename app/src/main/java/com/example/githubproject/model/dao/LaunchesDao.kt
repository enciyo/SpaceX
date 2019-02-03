package com.example.githubproject.model.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.githubproject.model.data.Launches
import io.reactivex.Single

@Dao
interface LaunchesDao {

    @Query("SELECT * FROM Launches")
    fun getAllTask(): Single<List<Launches>>

    @Query("SELECT * FROM Launches where `flightNumber` LIKE  :value ")
    fun findByName(value: Int): Single<List<Launches>>

    @Insert
    fun insert(launches: List<Launches>)

    @Delete
    fun delete(launches: Launches)

    @Update
    fun update(launches: Launches)
}