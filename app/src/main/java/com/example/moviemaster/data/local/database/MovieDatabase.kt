package com.example.moviemaster.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviemaster.data.local.dao.MovieDao
import com.example.moviemaster.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}