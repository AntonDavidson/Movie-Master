package com.example.moviemaster.data.remote.movies


interface ApiMapper<I,O> {
    fun mapToDomain(input: I): O
}
