package com.example.moviemaster.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviemaster.common.constants.DB_NAME
import com.example.moviemaster.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM $DB_NAME WHERE id = :movieId")
    suspend fun deleteMovie(movieId: Long)

    @Query("SELECT * FROM $DB_NAME ")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM $DB_NAME WHERE id = :id")
    suspend fun getMovieById(id: Long): MovieEntity?
}