package com.example.moviemaster.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.moviemaster.common.constants.DB_NAME
import com.example.moviemaster.data.local.entity.MovieEntity

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM $DB_NAME WHERE id = :movieId")
    abstract suspend fun deleteMovie(movieId: Long)

    @Query("SELECT * FROM $DB_NAME ")
    abstract suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM $DB_NAME WHERE id = :id")
    abstract suspend fun getMovieById(id: Long): MovieEntity?


    @Transaction
    @Query("SELECT * FROM $DB_NAME ")
    suspend fun removeAndUpdate(movieId: Long): List<MovieEntity> {
        deleteMovie(movieId)
        return getAllMovies()
    }
}