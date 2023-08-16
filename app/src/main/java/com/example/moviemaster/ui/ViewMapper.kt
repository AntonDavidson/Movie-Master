package com.example.moviemaster.ui

interface ViewMapper<I, O> {

    suspend fun mapToView(input: I): O
}