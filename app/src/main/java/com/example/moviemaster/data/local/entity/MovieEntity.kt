package com.example.moviemaster.data.local.entity

import androidx.room.Entity
import com.example.moviemaster.common.constants.DB_NAME


@Entity(primaryKeys = [("id")], tableName = DB_NAME)
data class MovieEntity(
    val favorite:Boolean,
    val posterPath: String,
    val id: Long
)
