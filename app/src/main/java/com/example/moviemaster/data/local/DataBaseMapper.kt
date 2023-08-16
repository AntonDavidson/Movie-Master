package com.example.moviemaster.data.local

interface DataBaseMapper<I,O> {
    fun mapToDomain(input:I):O
}